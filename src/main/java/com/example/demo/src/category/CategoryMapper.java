package com.example.demo.src.category;

import com.example.demo.src.category.model.response.GetCategoryRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    List<GetCategoryRes> getCategories();

    List<GetCategoryRes> getCategoriesByServiceId(int serviceId);

}
