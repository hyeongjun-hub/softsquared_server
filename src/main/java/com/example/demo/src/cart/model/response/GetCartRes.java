package com.example.demo.src.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCartRes {
    private int userCartId;
    private String restaurantName;
    private String menuImageUrl;
    private String menuName;
    private int menuPrice;
    private String additionalMenuName;
    private int additionalMenuPrice;
    private int amount;
    private int priceSum;
}
