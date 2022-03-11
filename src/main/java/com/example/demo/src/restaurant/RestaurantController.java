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

    /**
     * 카테고리 식당 조회 API
     * 카데고리 별 식당 조회와 정렬이 가능
     *
     * @param categoryId
     * @param sortQuery
     * @return BaseResponse<List < GetRestaurantRes>>
     */
    @GetMapping("/{categoryId}")
    public BaseResponse<List<GetRestaurantRes>> getRestaurants(@PathVariable("categoryId") int categoryId, @RequestParam(value = "sortQuery", required = false) String sortQuery) throws BaseException {
        if (sortQuery == null) {
            List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurants(categoryId);
            return new BaseResponse<>(getRestaurantRes);
        }
        System.out.println("sortQuery = " + sortQuery);
        logger.warn(sortQuery);
        List<GetRestaurantRes> getRestaurantRes = restaurantProvider.getRestaurantsWithSort(categoryId, sortQuery);
        return new BaseResponse<>(getRestaurantRes);
    }

    /**
     * 식당 조회 API (메뉴탭)
     * 메뉴탭에 해당하는 내용들을 조회하는 API
     *
     * @param restaurantId
     * @return BaseResponse<List < GetRestaurantDetailRes>>
     */
    @GetMapping("/{restaurantId}/detail")
    public BaseResponse<List<GetRestaurantDetailRes>> getRestaurantDetail(@PathVariable("restaurantId") int restaurantId) throws BaseException {
        List<GetRestaurantDetailRes> getRestaurantDetailRes = restaurantProvider.getRestaurantDetail(restaurantId);
        return new BaseResponse<>(getRestaurantDetailRes);
    }
}
