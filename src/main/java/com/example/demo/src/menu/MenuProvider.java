package com.example.demo.src.menu;

import com.example.demo.config.BaseException;
import com.example.demo.src.menu.model.GetMenuRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class MenuProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuDao menuDao;

    public List<GetMenuRes> getMenu(int menuId) throws BaseException {
        try{
            List<GetMenuRes> getMenuRes = menuDao.getMenu(menuId);
            return getMenuRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
