package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.GetCategoryRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class CategoryProvider {
    private final CategoryDao categoryDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<GetCategoryRes> getCategories() throws BaseException{
        try{
            List<GetCategoryRes> getCategoriesRes = categoryDao.getCategories();
            return getCategoriesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCategoryRes> getCategoriesByServiceId(int serviceId) throws BaseException{
        try{
            List<GetCategoryRes> getCategoriesRes = categoryDao.getCategoriesByServiceId(serviceId);
            return getCategoriesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
