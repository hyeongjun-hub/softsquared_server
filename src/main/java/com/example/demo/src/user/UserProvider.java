package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class UserProvider {

    private final UserMapper userMapper;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<GetUserRes> getUsers() throws BaseException{
        try{
            return userMapper.getUsers();
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetUserRes getUser(int userId) throws BaseException {
        if (!userMapper.getUserStatus(userId).equals("Y")) {
            throw new BaseException(USERS_STATUS_NOT_Y);
        }
        try {
            return userMapper.getUser(userId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getPoint(int userId) throws BaseException {
        try{
            int point = userMapper.getPoint(userId);
            return point;
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
    }

    public List<GetCouponRes> getCoupons(int userId) throws BaseException{
        try{
            List<GetCouponRes> getCouponRes = userMapper.getCoupons(userId);
            return getCouponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetPresentRes> getPresents(int userId) throws BaseException{
        try{
            List<GetPresentRes> getPresentRes = userMapper.getPresents(userId);
            return getPresentRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetAddressRes> getAddress(int userId) throws BaseException{
        try{
            List<GetAddressRes> getAddressRes = userMapper.getAddress(userId);
            return getAddressRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
