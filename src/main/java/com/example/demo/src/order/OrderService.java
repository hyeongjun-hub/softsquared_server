package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.PostOrderReq;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class OrderService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderDao orderDao;

    public int createOrder(PostOrderReq postOrderReq) throws BaseException {
        try{
            int orderListId = orderDao.createOrder(postOrderReq);
            orderDao.updatePrice(orderListId);
            return orderListId;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
