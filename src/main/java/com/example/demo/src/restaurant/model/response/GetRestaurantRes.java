package com.example.demo.src.restaurant.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetRestaurantRes {
    private int categoryId;
    private int restaurantId;
    private String restaurantName;
    private int reviewCount;
    private Double reviewAvg;
    private String avatarUrl;
    private int deliveryMinMoney;
    private int deliveryStart;
    private int deliveryEnd;
    private int deliveryTipMin;
    private int deliveryTipMax;
    private String orderMethod;
}
