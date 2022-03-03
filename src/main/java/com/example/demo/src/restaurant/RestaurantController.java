package com.example.demo.src.restaurant;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurant.model.GetRestaurantDetailRes;
import com.example.demo.src.restaurant.model.GetRestaurantRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantProvider restaurantProvider;

    @GetMapping("/{categoryId}")
    public BaseResponse<List<GetRestaurantRes>> getRestaurants(@PathVariable("categoryId") int categoryId) {
        try {
            List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants(categoryId);
            return new BaseResponse<>(getRestaurantRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{restaurantId}/detail")
    public BaseResponse<List<GetRestaurantDetailRes>> getRestaurantDetail(@PathVariable("restaurantId") int restaurantId) {
        try {
            List<GetRestaurantDetailRes> getRestaurantDetailRes = restaurantProvider.getRestaurantDetail(restaurantId);
            return new BaseResponse<>(getRestaurantDetailRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
