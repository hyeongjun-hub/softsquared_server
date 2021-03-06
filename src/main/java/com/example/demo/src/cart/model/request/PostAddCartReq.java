package com.example.demo.src.cart.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAddCartReq {
    private int menuId;
    private int amount;
    private int userCartId;
    private int orderDetailId;
}
