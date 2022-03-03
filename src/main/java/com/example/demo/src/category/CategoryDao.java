package com.example.demo.src.category;

import com.example.demo.src.category.model.GetCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCategoryRes> getCategories(){
        String getCategoriesQuery = "select * from Category";
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName"),
                        rs.getString("categoryImageUrl"),
                        rs.getInt("serviceId")
                )
        );
    }

    public List<GetCategoryRes> getCategoriesByServiceId(String serviceId){
        String getCategoryQuery = "select * from Category where serviceId = ?";
        int getCategoryParams = Integer.parseInt(serviceId);
        return this.jdbcTemplate.query(getCategoryQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName"),
                        rs.getString("categoryImageUrl"),
                        rs.getInt("serviceId")
                ), getCategoryParams);
    }

}
