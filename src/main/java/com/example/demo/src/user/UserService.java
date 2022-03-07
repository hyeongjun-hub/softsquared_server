package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.PostLoginRes;
import com.example.demo.src.user.model.response.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
        if(this.checkEmail(postUserReq.getUserEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int userId = userDao.createUser(postUserReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(userId, jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editUser(int UserId, User user) throws BaseException {
        //중복
        if(this.checkEmail(user.getUserEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        try{
            userDao.editUser(UserId, user);
//            if(result == 0){
//                throw new BaseException(EDIT_FAIL_CONTENT);
//            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delUser(PostUserDelReq postUserDelReq) throws BaseException {
        try{
            userDao.delUser(postUserDelReq);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public int checkEmail(String email) throws BaseException{
        try{
            return userDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException{
        User user = userDao.getPwd(postLoginReq);
        String encryptPwd;
        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(user.getPassword().equals(encryptPwd)){
            int userId = user.getUserId();
            String jwt = jwtService.createJwt(userId);
            return new PostLoginRes(userId,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }


    public int createAddress(int userId, PostAddressReq postAddressReq) throws BaseException {
        try{
            return userDao.createAddress(userId, postAddressReq);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editAddress(int addressId, PatchAddressReq patchAddressReq) throws BaseException {
        try{
            userDao.editAddress(addressId, patchAddressReq);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delAddress(int addressId) throws BaseException {
        try{
            userDao.delAddress(addressId);
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
