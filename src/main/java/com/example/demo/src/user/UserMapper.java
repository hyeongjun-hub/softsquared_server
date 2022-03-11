package com.example.demo.src.user;

import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.GetAddressRes;
import com.example.demo.src.user.model.response.GetCouponRes;
import com.example.demo.src.user.model.response.GetPresentRes;
import com.example.demo.src.user.model.response.GetUserRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<GetUserRes> getUsers();

    GetUserRes getUser(int userId);

    int createUser(PostUserReq postUserReq, String platform);

    int checkEmail(String email);

    String getPlatform(String email);

    int checkAddress(String address);

    int checkAddressName(String address);

    int editUser(int userId, User user);

    int delUser(PostUserDelReq postUserDelReq);

    User getLoginUser(PostLoginReq postLoginReq);

    int getUserId(int addressId);

    int getUserIdByEmail(String userEmail);

    int getPoint(int userId);

    List<GetCouponRes> getCoupons(int userId);

    List<GetPresentRes> getPresents(int userId);

    List<GetAddressRes> getAddress(int userId);

    int createAddress(int userId, PostAddressReq postAddressReq);

    void editAddress(int addressId, PatchAddressReq patchAddressReq);

    void delAddress(int addressId);

    String getAddressStatus(int addressId);

    String getUserStatus(int userId);

}
