package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private int orderListId;
    private String avatarUrl;
    private String restaurantName;
    private String menuName;
    private int finalPrice;
    private String createdAt;
}
