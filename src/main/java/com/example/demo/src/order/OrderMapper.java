package com.example.demo.src.order;

import com.example.demo.src.order.model.request.PostOrderReq;
import com.example.demo.src.order.model.response.GetOrderDetailRes;
import com.example.demo.src.order.model.response.GetOrderRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    int createOrder(PostOrderReq postOrderReq);

    void updateCartStatus(int userCartId);

    void updateCouponStatus(int couponId);

    void updatePresentStatus(int presentId);

    void updatePoint(int userId, int point);

    int calculateFinalPrice(int OrderListId);

    void updatePrice(int userId, int finalPrice);

    List<GetOrderRes> getOrders(int userId);

    List<GetOrderDetailRes> getOrder(int orderListId);

}
