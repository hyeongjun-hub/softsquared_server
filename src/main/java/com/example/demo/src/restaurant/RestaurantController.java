package com.example.demo.src.restaurant;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurant.model.response.GetRestaurantDetailRes;
import com.example.demo.src.restaurant.model.response.GetRestaurantRes;
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
    public BaseResponse<List<GetRestaurantRes>> getRestaurants(@PathVariable("categoryId") int categoryId, @RequestParam(value = "sortQuery", required = false) String sortQuery) {
        try {
            if(sortQuery == null){
                List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants(categoryId);
                return new BaseResponse<>(getRestaurantRes);
            }
            System.out.println("sortQuery = " + sortQuery);
            logger.warn(sortQuery);
            List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurantsWithSort(categoryId, sortQuery);
            return new BaseResponse<>(getRestaurantRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @GetMapping("/{categoryId}")
//    public BaseResponse<List<GetRestaurantRes>> getRestaurants(@PathVariable("categoryId") int categoryId) {
//        try {
//            List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants(categoryId);
//            return new BaseResponse<>(getRestaurantRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//

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
