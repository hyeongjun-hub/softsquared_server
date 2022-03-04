package com.example.demo.src.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCartRes {
    private int userCartId;
    private String restaurantName;
    private String menuImageUrl;
    private String menuName;
    private int menuPrice;
    private String additionalMenuName;
    private int additionalMenuPrice;
    private int amount;
}
