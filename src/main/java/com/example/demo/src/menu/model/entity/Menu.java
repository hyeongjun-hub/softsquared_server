package com.example.demo.src.menu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Menu {
    private int menuId;
    private int bigMenuId;
    private String menuName;
    private int price;
    private String populer;
    private String menuDetail;
    private String menuSummary;
    private String menuImageUrl;
    private String createdAt;
    private String updatedAt;
    private String status;
}
