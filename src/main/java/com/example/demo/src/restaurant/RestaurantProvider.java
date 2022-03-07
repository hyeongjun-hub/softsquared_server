package com.example.demo.src.restaurant;

import com.example.demo.config.BaseException;
import com.example.demo.src.restaurant.model.response.GetRestaurantDetailRes;
import com.example.demo.src.restaurant.model.response.GetRestaurantRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class RestaurantProvider {

    private final RestaurantDao restaurantDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<GetRestaurantRes> getRestaurants(int categoryId) throws BaseException {
        try{
            List<GetRestaurantRes> getRestaurantRes = restaurantDao.getRestaurants(categoryId);
            return getRestaurantRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetRestaurantDetailRes> getRestaurantDetail(int restaurantId) throws BaseException {
        try{
            List<GetRestaurantDetailRes> getRestaurantDetailRes = restaurantDao.getRestaurantDetail(restaurantId);
            return getRestaurantDetailRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
