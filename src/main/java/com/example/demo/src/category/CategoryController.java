package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.response.GetCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryProvider categoryProvider;

    @Autowired
    public CategoryController(CategoryProvider categoryProvider, CategoryDao categoryDao) {
        this.categoryProvider = categoryProvider;
    }

    @GetMapping("") // (GET) /categories
    public BaseResponse<List<GetCategoryRes>> getCategories(){
        try{
            List<GetCategoryRes> getCategoriesRes;
            getCategoriesRes = categoryProvider.getCategories();
            return new BaseResponse<>(getCategoriesRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("{serviceId}") // (GET) /categories/:serviceId
    public BaseResponse<List<GetCategoryRes>> getCategoriesByServiceId(@PathVariable int serviceId){
        try{
            List<GetCategoryRes> getCategoriesRes = categoryProvider.getCategoriesByServiceId(serviceId);
            return new BaseResponse<>(getCategoriesRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



}
