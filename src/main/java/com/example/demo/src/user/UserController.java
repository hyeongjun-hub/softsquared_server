package com.example.demo.src.user;

import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;

    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService) {
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     *
     * @return BaseResponse<List < GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/all") // (GET) 127.0.0.1:9000/users/all
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        // TODO : status check
        try {
            if (Email == null) {
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userId
     *
     * @return BaseResponse<GetUserRes>
     */
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/users
    public BaseResponse<GetUserRes> getUser() {
        // Get User
        try {
            int userId = jwtService.getUserId();
            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 회원가입 API
     * [POST] /users
     *
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")  // (POST) 127.0.0.1:9000/users
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        // TODO: 비밀번호 최소 길이
        // TODO:
        if (postUserReq.getUserEmail() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        //이메일 정규표현
        if (!isRegexEmail(postUserReq.getUserEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 로그인 API
     * [POST] /users/logIn
     *
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")  // (POST) 127.0.0.1:9000/users/login
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        if (!isRegexEmail(postLoginReq.getUserEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        if (postLoginReq.getPassword().length() < 8) {
            return new BaseResponse<>(POST_USERS_PASSWORD_MIN);
        }
        try {
            PostLoginRes postLoginRes = userService.login(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userIdx
     *
     * @return BaseResponse<String>
     */

    @ResponseBody
    @PatchMapping("/detail")  // (GET) 127.0.0.1:9000/users/:userId
    public BaseResponse<PatchUserRes> editUser(@RequestBody User user) {
        if (!isRegexEmail(user.getUserEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        try {
            //jwt에서 id 추출.
            int userId = jwtService.getUserId();
            PatchUserRes patchUserRes = userService.editUser(userId, user);
            return new BaseResponse<>(patchUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PatchMapping("/delete")
    public BaseResponse<String> delUser() {
        try {
            //jwt에서 idx 추출.
            int userId = jwtService.getUserId();

            // 상태값 D로 변경
            PostUserDelReq postUserDelReq = new PostUserDelReq(userId);
            userService.delUser(postUserDelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/point")
    public BaseResponse<Integer> getPoint() {
        try {
            int userId = jwtService.getUserId();
            int point = userProvider.getPoint(userId);
            return new BaseResponse<>(point);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/coupon")
    public BaseResponse<List<GetCouponRes>> getCoupons() {
        try {
            int userId = jwtService.getUserId();
            List<GetCouponRes> getCouponRes  = userProvider.getCoupons(userId);
            return new BaseResponse<>(getCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/present")
    public BaseResponse<List<GetPresentRes>> getPresents() {
        try {
            int userId = jwtService.getUserId();
            List<GetPresentRes> getPresentRes  = userProvider.getPresents(userId);
            return new BaseResponse<>(getPresentRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/address")
    public BaseResponse<List<GetAddressRes>> getAddress() {
        try {
            int userId = jwtService.getUserId();
            List<GetAddressRes> getAddressRes  = userProvider.getAddress(userId);
            return new BaseResponse<>(getAddressRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PostMapping("/address")
    public BaseResponse<Integer> createAddress( @RequestBody PostAddressReq postAddressReq) {
        try {
            int userId = jwtService.getUserId();
            int addressId = userService.createAddress(userId, postAddressReq);
            return new BaseResponse<>(addressId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/{addressId}/address")
    public BaseResponse<String> editAddress(@PathVariable("addressId") int addressId, @RequestBody PatchAddressReq patchAddressReq) {
        try {
            userService.editAddress(addressId, patchAddressReq);
            return new BaseResponse<>("");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/{addressId}/address/delete")
    public BaseResponse<String> delAddress(@PathVariable("addressId") int addressId) {
        try {
            userService.delAddress(addressId);
            return new BaseResponse<>("");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
