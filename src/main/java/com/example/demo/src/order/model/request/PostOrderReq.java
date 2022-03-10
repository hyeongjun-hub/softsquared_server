package com.example.demo.src.order.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrderReq {
    private int userCartId;
    private String request;
    private String toRider;
    private int usePoint;
    private int addressId;
    private int couponId;
    private int presentId;
    private int paymentMethodId;
    private int orderListId;
}
