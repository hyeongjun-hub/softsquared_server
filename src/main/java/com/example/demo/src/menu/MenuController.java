package com.example.demo.src.menu;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.menu.model.response.GetMenuRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuProvider menuProvider;

    /**
     * 특정 메뉴 조회 API
     *
     * @param menuId
     * @return BaseResponse<List < GetMenuRes>>
     */
    @GetMapping("/{menuId}")
    public BaseResponse<List<GetMenuRes>> getMenu(@PathVariable("menuId") int menuId) throws BaseException {
        List<GetMenuRes> getMenuRes = menuProvider.getMenu(menuId);
        return new BaseResponse<>(getMenuRes);
    }


}
