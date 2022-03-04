package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.GetOrderDetailRes;
import com.example.demo.src.order.model.GetOrderRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class OrderProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final OrderDao orderDao;

    public List<GetOrderRes> getOrders(int userId) throws BaseException {
        try{
            List<GetOrderRes> getOrderRes = orderDao.getOrders(userId);
            return getOrderRes;
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetOrderDetailRes> getOrder(int orderListId) throws BaseException {
        try {
            List<GetOrderDetailRes> getOrderDetailRes = orderDao.getOrder(orderListId);
            return getOrderDetailRes;
        }
        catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
