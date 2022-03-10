package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.request.PostAddAdditionalCartReq;
import com.example.demo.src.cart.model.request.PostAddCartReq;
import com.example.demo.src.cart.model.request.PostCreateCartReq;
import com.example.demo.src.cart.model.response.PostAddCartRes;
import com.example.demo.src.cart.model.response.PostCartRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.POST_CARTS_INVALID_MENU;

@Service
@AllArgsConstructor
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartMapper cartMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public PostCartRes createCart(int userId) throws BaseException {
        try{
            int userCartId = 0;
            PostCreateCartReq postCreateCartReq = new PostCreateCartReq(userId, userCartId);
            cartMapper.createCart(postCreateCartReq);
            userCartId = postCreateCartReq.getUserCartId();
            return new PostCartRes(userCartId);
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public PostAddCartRes addMenu(int userCartId, PostAddCartReq postAddCartReq) throws BaseException {
        if (postAddCartReq.getMenuId() == 0) {
            throw new BaseException(POST_CARTS_INVALID_MENU);
        }
        try{
            postAddCartReq.setUserCartId(userCartId);
            cartMapper.addMenu(postAddCartReq);
            int orderDetailId = postAddCartReq.getOrderDetailId();
            System.out.println("orderDetailId = " + orderDetailId);
            cartMapper.updateCart(userCartId, cartMapper.calculatePrice(orderDetailId));
            PostAddCartRes postAddCartRes = new PostAddCartRes(orderDetailId);
            return postAddCartRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public PostAddCartRes addAdditionalMenu(int userCartId, PostAddAdditionalCartReq postAddAdditionalCartReq) throws BaseException {
        try{
            postAddAdditionalCartReq.setUserCartId(userCartId);
            cartMapper.addAdditionalMenu(postAddAdditionalCartReq);
            int orderDetailId = postAddAdditionalCartReq.getOrderDetailId();
            PostAddCartRes postAddCartRes = new PostAddCartRes(orderDetailId);
            cartMapper.updateCartAdditional(userCartId, cartMapper.calculateAdditionalPrice(orderDetailId));
            return postAddCartRes;
        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delCart(int userCartId) throws BaseException {
        try{
            cartMapper.delCart(userCartId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
