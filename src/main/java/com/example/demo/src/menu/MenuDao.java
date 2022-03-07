package com.example.demo.src.menu;

import com.example.demo.src.menu.model.response.GetMenuRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MenuDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetMenuRes> getMenu(int menuId){
        String getMenuQuery = "select Menu.menuId as menuId, menuName, menuDetail, Menu.price as menuPrice, menuImageUrl, bigAdditionalMenuName, maxSelect, additionalMenuName, AM.price as additionalMenuPrice from Menu inner join BigAdditionalMenu BAM on Menu.menuId = BAM.menuId inner join AdditionalMenu AM on BAM.bigAdditionalMenuId = AM.bigAdditionalMenuId where Menu.menuId = ? and Menu.status = 'Y'";
        int getMenuParams = menuId;
        return this.jdbcTemplate.query(getMenuQuery,
                (rs, rowNum) -> new GetMenuRes(
                        rs.getInt("menuId"),
                        rs.getString("menuName"),
                        rs.getString("menuDetail"),
                        rs.getInt("menuPrice"),
                        rs.getString("menuImageUrl"),
                        rs.getString("bigAdditionalMenuName"),
                        rs.getInt("maxSelect"),
                        rs.getString("additionalMenuName"),
                        rs.getInt("additionalMenuPrice")),
                getMenuParams);
    }

}
