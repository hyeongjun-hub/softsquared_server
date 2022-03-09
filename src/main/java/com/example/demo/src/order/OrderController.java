package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.response.GetOrderDetailRes;
import com.example.demo.src.order.model.response.GetOrderRes;
import com.example.demo.src.order.model.request.PostOrderReq;
import com.example.demo.utils.JwtService;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderProvider orderProvider;
    private final OrderService orderService;
    private final JwtService jwtService;

    @PostMapping("/new")
    public BaseResponse<Integer> createOrder(@RequestBody PostOrderReq postOrderReq) {
        try{
            int userId = jwtService.getUserId();
            int resultInt = orderService.createOrder(userId, postOrderReq);
            return new BaseResponse<>(resultInt);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/list")
    public BaseResponse<List<GetOrderRes>> getOrders() {
        try{
            int userId = jwtService.getUserId();
            List<GetOrderRes> getOrderRes = orderProvider.getOrders(userId);
            return new BaseResponse<>(getOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{orderListId}/detail")
    public BaseResponse<List<GetOrderDetailRes>> getOrder(@PathVariable("orderListId") int orderListId) {
        try{
            List<GetOrderDetailRes> getOrderDetailRes = orderProvider.getOrder(orderListId);
            return new BaseResponse<>(getOrderDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
