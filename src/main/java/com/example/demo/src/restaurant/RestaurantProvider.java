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

    private final RestaurantMapper restaurantMapper;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<GetRestaurantRes> getRestaurants(int categoryId) throws BaseException {
        try{
            List<GetRestaurantRes> getRestaurantRes = restaurantMapper.getRestaurants(categoryId);
            return getRestaurantRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetRestaurantRes> getRestaurantsWithSort(int categoryId, String sortQuery) throws BaseException {
        try{
            List<GetRestaurantRes> getRestaurantRes = restaurantMapper.getRestaurantsWithSort(categoryId, sortQuery);
            System.out.println("sortQuery2 = " + sortQuery);
            return getRestaurantRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetRestaurantDetailRes> getRestaurantDetail(int restaurantId) throws BaseException {
        try{
            List<GetRestaurantDetailRes> getRestaurantDetailRes = restaurantMapper.getRestaurantDetail(restaurantId);
            return getRestaurantDetailRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
