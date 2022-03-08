package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.request.PostOrderReq;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderDao orderDao;

    @Transactional
    public int createOrder(PostOrderReq postOrderReq) throws BaseException {
        try{
            int orderListId = orderDao.createOrder(postOrderReq);
            // userCart의 status를 N으로 변경
            orderDao.updateCartStatus(postOrderReq.getUserCartId());
            // coupon의 status를 N으로 변경
            orderDao.updateCouponStatus(postOrderReq.getCouponId());
            // present의 status를 N으로 변경
            orderDao.updatePresentStatus(postOrderReq.getPresentId());
            orderDao.updatePrice(orderListId);
            return orderListId;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
