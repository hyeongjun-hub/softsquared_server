package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class GetOrderDetailRes {
    private int orderListId;
    private int price;
    private int amount;
    private String restaurantName;
    private String menuName;
    private String additionalMenuName;
    private int finalPrice;
    private String createdAt;
    private String restaurantPhoneNumber;
    private String request;
    private String toRider;
    private String spoon;
    private String address;
    private String phoneNumber;
}
