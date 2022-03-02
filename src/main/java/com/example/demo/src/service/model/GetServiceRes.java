package com.example.demo.src.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GetServiceRes {
    private int serviceId;
    private String serviceName;
    private String serviceImageUrl;
}

