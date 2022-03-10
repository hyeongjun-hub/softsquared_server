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

    private OrderMapper orderMapper;

    @Transactional
    public int createOrder(int userId, PostOrderReq postOrderReq) throws BaseException {
        try{
            orderMapper.createOrder(postOrderReq);
            int orderListId = postOrderReq.getOrderListId();
            System.out.println("orderListId = " + orderListId);
            // userCart의 status를 N으로 변경
            orderMapper.updateCartStatus(postOrderReq.getUserCartId());
            // coupon의 status를 N으로 변경
            orderMapper.updateCouponStatus(postOrderReq.getCouponId());
            // present의 status를 N으로 변경
            orderMapper.updatePresentStatus(postOrderReq.getPresentId());
            // User의 point를 차감
            orderMapper.updatePoint(userId, postOrderReq.getUsePoint());
            int finalPrice = orderMapper.calculateFinalPrice(orderListId);
            orderMapper.updatePrice(orderListId, finalPrice);
            return orderListId;
        } catch(Exception exception){
            System.out.println("exception = " + exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
