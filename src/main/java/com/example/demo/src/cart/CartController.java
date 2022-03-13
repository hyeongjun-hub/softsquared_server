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

import static com.example.demo.config.BaseResponseStatus.POST_CARTS_INVALID_MENU;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartProvider cartProvider;
    private final CartService cartService;
    private final JwtService jwtService;

    /**
     * 장바구니 생성 API
     *
     * @return BaseResponse<PostCartRes>
     */
    @PostMapping("/new")
    public BaseResponse<PostCartRes> createCart() throws BaseException {
        int userId = jwtService.getUserId();
        PostCartRes postCartRes = cartService.createCart(userId);
        return new BaseResponse<>(postCartRes);
    }

    /**
     * 장바구니 조회 API
     *
     * @param userCartId
     * @return BaseResponse<List < GetCartRes>>
     */
    @GetMapping("/{userCartId}")
    public BaseResponse<List<GetCartRes>> getUserCart(@PathVariable int userCartId) throws BaseException {
        List<GetCartRes> getCartRes = cartProvider.getCart(userCartId);
        return new BaseResponse<>(getCartRes);
    }

    /**
     * 장바구니 메뉴담기 API
     *
     * @param userCartId
     * @param postAddCartReq
     * @return BaseResponse<PostAddCartRes>
     */
    @PostMapping("{userCartId}/menu")
    public BaseResponse<PostAddCartRes> addMenu(@PathVariable int userCartId, @RequestBody PostAddCartReq postAddCartReq) throws BaseException {
        if(postAddCartReq.getMenuId() == 0){
            return new BaseResponse<>(POST_CARTS_INVALID_MENU);
        }
        PostAddCartRes postAddCartRes = cartService.addMenu(userCartId, postAddCartReq);
        return new BaseResponse<>(postAddCartRes);
    }

    /**
     * 장바구니 추가메뉴담기 API
     *
     * @param userCartId
     * @param postAddAdditionalCartReq
     * @return BaseResponse<PostAddCartRes>
     */
    @PostMapping("{userCartId}/additional-menu")
    public BaseResponse<PostAddCartRes> addAdditionalMenu(@PathVariable int userCartId, @RequestBody PostAddAdditionalCartReq postAddAdditionalCartReq) throws BaseException {
        if(postAddAdditionalCartReq.getAdditionalMenuId() == 0){
            return new BaseResponse<>(POST_CARTS_INVALID_MENU);
        }
        PostAddCartRes postAddCartRes = cartService.addAdditionalMenu(userCartId, postAddAdditionalCartReq);
        return new BaseResponse<>(postAddCartRes);
    }

    /**
     * 장바구니 삭제 API
     *
     * @param userCartId
     * @return BaseResponse<String>
     */
    @PatchMapping("{userCartId}/delete")
    public BaseResponse<String> delCart(@PathVariable int userCartId) throws BaseException {
        cartService.delCart(userCartId);
        String result = "";
        return new BaseResponse<>(result);
    }

}
