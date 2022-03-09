package com.example.demo.src.restaurant;

import com.example.demo.src.restaurant.model.response.GetRestaurantDetailRes;
import com.example.demo.src.restaurant.model.response.GetRestaurantRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RestaurantMapper {

    List<GetRestaurantRes> getRestaurants(int categoryId);

    List<GetRestaurantDetailRes> getRestaurantDetail(int restaurantId);

}
