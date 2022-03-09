package com.example.demo.src.menu;

import com.example.demo.src.menu.model.response.GetMenuRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    List<GetMenuRes> getMenu(int menuId);
}
