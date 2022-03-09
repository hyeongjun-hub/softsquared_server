package com.example.demo.src.service;

import com.example.demo.src.service.model.response.GetServiceRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServiceMapper {
    List<GetServiceRes> getServices();
}
