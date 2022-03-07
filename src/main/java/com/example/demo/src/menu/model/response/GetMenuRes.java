package com.example.demo.src.menu.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuRes {
    private int menuId;
    private String menuName;
    private String menuDetail;
    private int menuPrice;
    private String menuImageUrl;
    private String bigAdditionalMenuName;
    private int maxSelect;
    private String additionalMenuName;
    private int additionalMenuPrice;

}
