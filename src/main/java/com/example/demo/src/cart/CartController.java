package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cart.model.request.PostAddAdditionalCartReq;
import com.example.demo.src.cart.model.request.PostAddCartReq;
import com.example.demo.src.cart.model.response.GetCartRes;
import com.example.demo.src.cart.model.response.PostAddCartRes;
import com.example.demo.src.cart.model.response.PostCartRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartProvider cartProvider;
    private final CartService cartService;
    private final JwtService jwtService;

    @PostMapping("/new")
    public BaseResponse<PostCartRes> createCart() {
        try{
            int userId = jwtService.getUserId();
            PostCartRes postCartRes = cartService.createCart(userId);
            return new BaseResponse<>(postCartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{userCartId}")
    public BaseResponse<List<GetCartRes>> getUserCart(@PathVariable int userCartId){
        try{
            List<GetCartRes> getCartRes = cartProvider.getCart(userCartId);
            return new BaseResponse<>(getCartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    //POST (menu를 추가)
    @PostMapping("{userCartId}/menu")
    public BaseResponse<PostAddCartRes> addMenu(@PathVariable int userCartId, @RequestBody PostAddCartReq postAddCartReq) {
        try{
            PostAddCartRes postAddCartRes = cartService.addMenu(userCartId, postAddCartReq);
            return new BaseResponse<>(postAddCartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    //POST (additionalMenu를 추가)
    @PostMapping("{userCartId}/additional-menu")
    public BaseResponse<PostAddCartRes> addAdditionalMenu(@PathVariable int userCartId, @RequestBody PostAddAdditionalCartReq postAddAdditionalCartReq) {
        try{
            PostAddCartRes postAddCartRes = cartService.addAdditionalMenu(userCartId, postAddAdditionalCartReq);
            return new BaseResponse<>(postAddCartRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //Post (장바구니 삭제)
    @PatchMapping("{userCartId}/delete")
    public BaseResponse<String> delCart(@PathVariable int userCartId) {
        try{
            cartService.delCart(userCartId);
            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
