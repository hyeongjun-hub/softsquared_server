package com.example.demo.src.restaurant.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetRestaurantDetailRes {
    private int restaurantId;
    private String restaurantName;
    private String url;
    private String clean;
    private int deliveryMinMoney;
    private String paymentMethod;
    private int deliveryStart;
    private int deliveryEnd;
    private int deliveryTipMin;
    private int deliveryTipMax;
    private int takeOutMinMoney;
    private String orderMethod;
    private int cookingTimeStart;
    private int cookingTimeEnd;
    private String restaurantAddress;
    private String paymentMethodTO;
    private String menuDetail;
    private String bigMenuName;
    private String menuName;
    private String popular;
    private String menuSummary;
    private String menuImageUrl;
    private int price;
}
