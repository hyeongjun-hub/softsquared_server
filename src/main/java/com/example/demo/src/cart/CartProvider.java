package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.GetCartRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class CartProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartDao cartDao;

    public List<GetCartRes> getCart(int userCartId) throws BaseException {
        try{
            List<GetCartRes> getCartRes = cartDao.getCart(userCartId);
            return getCartRes;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
