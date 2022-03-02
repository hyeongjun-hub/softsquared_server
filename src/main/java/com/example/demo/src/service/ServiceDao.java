package com.example.demo.src.service;

import com.example.demo.src.service.model.GetServiceRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ServiceDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetServiceRes> getServices(){
        String getServicesQuery = "select * from Service";
        return this.jdbcTemplate.query(getServicesQuery,
                (rs, rowNum) -> new GetServiceRes(
                        rs.getInt("serviceId"),
                        rs.getString("serviceName"),
                        rs.getString("serviceImageUrl")
                )
        );
    }

}
