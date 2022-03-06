package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.PostAddAdditionalCartReq;
import com.example.demo.src.cart.model.PostAddCartReq;
import com.example.demo.src.cart.model.PostAddCartRes;
import com.example.demo.src.cart.model.PostCartRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartDao cartDao;

    public PostCartRes createCart(int userId) throws BaseException {
        try{
            int userCartId = cartDao.createUserCart(userId);
            return new PostCartRes(userCartId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostAddCartRes addMenu(int userCartId, PostAddCartReq postAddCartReq) throws BaseException {
        try{
            int orderDetailId = cartDao.addMenu(userCartId, postAddCartReq);
            System.out.println("orderDetailId = " + orderDetailId);
            cartDao.updateCart(orderDetailId, userCartId);
            PostAddCartRes postAddCartRes = new PostAddCartRes(orderDetailId);
            return postAddCartRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostAddCartRes addAdditionalMenu(int userCartId, PostAddAdditionalCartReq postAddAdditionalCartReq) throws BaseException {
        try{
            int orderDetailId = cartDao.addAdditionalMenu(userCartId, postAddAdditionalCartReq);
            PostAddCartRes postAddCartRes = new PostAddCartRes(orderDetailId);
            cartDao.updateCartAdditional(orderDetailId, userCartId);
            return postAddCartRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delCart(int userCartId) throws BaseException {
        try{
            cartDao.delCart(userCartId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}