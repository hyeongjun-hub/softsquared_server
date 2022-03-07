package com.example.demo.src.category;

import com.example.demo.src.category.model.response.GetCategoryRes;
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
        String getCategoriesQuery = "select * from Category where status 'Y'";
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName"),
                        rs.getString("categoryImageUrl"),
                        rs.getInt("serviceId")
                )
        );
    }

    public List<GetCategoryRes> getCategoriesByServiceId(int serviceId){
        String getCategoryQuery = "select categoryId, categoryName, categoryImageUrl, serviceId from Category where serviceId = ? and status='Y'";
        int getCategoryParams = serviceId;
        return this.jdbcTemplate.query(getCategoryQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName"),
                        rs.getString("categoryImageUrl"),
                        rs.getInt("serviceId")
                ), getCategoryParams);
    }
}
