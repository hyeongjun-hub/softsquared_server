package com.example.demo.src.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String shareLink;
    private String restaurantPhoneNumber;
    private int deliveryMinMoney;
    private int takeOutMinMoney;
    private String orderMethod;
    private String paymentMethod;
    private String paymentMethodTO;
    private int deliveryStart;
    private int deliveryEnd;
    private int deliveryTipMin;
    private int deliveryTipMax;
    private int cookingTimeStart;
    private int cookingTimeEnd;
    private String openTime;
    private String holiday;
    private String menuDetail;
    private String infoDetail;
    private String notification;
    private double discountRate;
    private int discountAmount;
    private String discountDetail;
    private String avatarUrl;
    private String clean;
    private String origin;
    private int categoryId;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String ownerName;
    private String companyName;
    private String readyTime;
}
