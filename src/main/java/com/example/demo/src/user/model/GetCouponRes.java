package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCouponRes {
    private int couponId;
    private int amount;
    private int priceMin;
    private String startDate;
    private String deadLine;
    private String orderMethod;
    private String status;
}
