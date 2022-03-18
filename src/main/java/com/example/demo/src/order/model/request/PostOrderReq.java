package com.example.demo.src.order.model.request;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrderReq {
    private int userCartId;
    private String request;
    private String toRider;
    @PositiveOrZero(message = "0 또는 양수를 입력하세요.")
    private int usePoint;
    private int addressId;
    private int couponId;
    private int presentId;
    private int paymentMethodId;
    private int orderListId;
}
