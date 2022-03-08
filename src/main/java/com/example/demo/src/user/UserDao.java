package com.example.demo.src.user;

import com.example.demo.src.user.model.entity.User;
import com.example.demo.src.user.model.request.*;
import com.example.demo.src.user.model.response.GetAddressRes;
import com.example.demo.src.user.model.response.GetCouponRes;
import com.example.demo.src.user.model.response.GetPresentRes;
import com.example.demo.src.user.model.response.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User where status='Y'";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getInt("point"),
                        rs.getString("mailAccept"),
                        rs.getString("smsAccept"),
                        rs.getString("grade"),
                        rs.getString("status"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from User where userEmail =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getInt("point"),
                        rs.getString("mailAccept"),
                        rs.getString("smsAccept"),
                        rs.getString("grade"),
                        rs.getString("status")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userId){
        String getUserQuery = "select * from User where userId = ? and status = 'Y'";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getInt("point"),
                        rs.getString("mailAccept"),
                        rs.getString("smsAccept"),
                        rs.getString("grade"),
                        rs.getString("status")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (userName, password, userEmail) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getPassword(), postUserReq.getUserEmail()};
        System.out.println(createUserQuery);
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkEmail(String email){
        try{
            String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
            String checkEmailParams = email;
            return this.jdbcTemplate.queryForObject(checkEmailQuery,
                    int.class,
                    checkEmailParams);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int checkAddress(String address){
        try{
            String checkAddressQuery = "select exists(select address from Address where address = ?)";
            String checkAddressParams = address;
            return this.jdbcTemplate.queryForObject(checkAddressQuery,
                    int.class,
                    checkAddressParams);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int checkAddressName(String address){
        try{
            String checkAddressNameQuery = "select exists(select addressName from Address where addressName = ?)";
            String checkAddressNameParams = address;
            return this.jdbcTemplate.queryForObject(checkAddressNameQuery,
                    int.class,
                    checkAddressNameParams);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set userName = ? where userId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public int editUser(int userId, User user){
        String editUserQuery = "UPDATE User SET userName = ?, userEmail = ? , password = ?, profileImageUrl=?, phoneNumber=?, mailAccept =?, smsAccept=? WHERE userId = ?";
        Object[] editUserParams = new Object[]{user.getUserName(), user.getUserEmail(), user.getPassword(), user.getProfileImageUrl(), user.getPhoneNumber(), user.getMailAccept(), user.getSmsAccept() ,userId};
        return this.jdbcTemplate.update(editUserQuery, editUserParams);
    }

    public int delUser(PostUserDelReq postUserDelReq){
        String deleteUserQuery = "update User set status = 'D' where userId = ?";
        int deleteUserParams = postUserDelReq.getUserId();
        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }

    public User getLoginUser(PostLoginReq postLoginReq){
        try {
            String getPwdQuery = "select * from User where userEmail = ?";
            String getPwdParams = postLoginReq.getUserEmail();
            return this.jdbcTemplate.queryForObject(getPwdQuery,
                    (rs, rowNum) -> new User(
                            rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("userEmail"),
                            rs.getString("password"),
                            rs.getString("profileImageUrl"),
                            rs.getString("phoneNumber"),
                            rs.getInt("point"),
                            rs.getString("mailAccept"),
                            rs.getString("smsAccept"),
                            rs.getString("grade"),
                            rs.getString("status")
                    ),
                    getPwdParams
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int getUserId(int addressId){
        String getUserIdQuery = "select userId from Address where addressId = ?";
        int getUserIdParams = addressId;
        return this.jdbcTemplate.queryForObject(getUserIdQuery, int.class, getUserIdParams);
    }

    public int getPoint(int userId) {
        String getPointQuery = "select point from User where userId = ?";
        int getPointParams = userId;
        int point = this.jdbcTemplate.queryForObject(getPointQuery,  int.class, getPointParams);
        return point;
    }

    public List<GetCouponRes> getCoupons(int userId) {
        String getCouponsQuery = "select Coupon.couponId couponId, amount, priceMin, startDate, Coupon.deadline deadLine, orderMethod, Coupon.status status from Coupon inner join RestaurantCoupon RC on Coupon.restaurantCouponId = RC.restaurantCouponId inner join User U on Coupon.userId = U.userId where U.userId = ? and Coupon.status = 'Y'";
        int getCouponsParams = userId;
        return this.jdbcTemplate.query(getCouponsQuery,
                (rs, rowNum) -> new GetCouponRes(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                ), getCouponsParams);
    }

    public List<GetPresentRes> getPresents(int userId) {
        String getPresentsQuery = "select presentId, price, deadline, Present.status as status from Present inner join User U on Present.userId = U.userId where Present.userId = ? and Present.status='Y'";
        int getPresentsParams = userId;
        return this.jdbcTemplate.query(getPresentsQuery,
                (rs, rowNum) -> new GetPresentRes(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                ), getPresentsParams);
    }

    public List<GetAddressRes> getAddress(int userId) {
        String getAddressQuery = "select Address.addressId, addressName, address, Address.status from Address inner join User U on Address.userId = U.userId where U.userId = ? and Address.status = 'Y'";
        int getAddressParams = userId;
        return this.jdbcTemplate.query(getAddressQuery,
                (rs, rowNum) -> new GetAddressRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ), getAddressParams);
    }

    public int createAddress(int userId, PostAddressReq postAddressReq) {
        String createAddressQuery = "INSERT INTO Address (addressName, address, userId) VALUES (?, ?, ?)";
        Object[] createAddressParams = new Object[]{postAddressReq.getAddressName(), postAddressReq.getAddress(), userId};
        this.jdbcTemplate.update(createAddressQuery, createAddressParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public void editAddress(int addressId, PatchAddressReq patchAddressReq) {
        String editAddressQuery = "Update Address SET addressName = ?, address = ? WHERE addressId = ?";
        Object[] editAddressParams = new Object[]{patchAddressReq.getAddressName(), patchAddressReq.getAddress(), addressId};
        this.jdbcTemplate.update(editAddressQuery, editAddressParams);
    }

    public void delAddress(int addressId) {
        String editAddressQuery = "Update Address SET status = 'D' WHERE addressId = ?";
        this.jdbcTemplate.update(editAddressQuery, addressId);
    }

    public String getAddressStatus(int addressId) {
        String getAddressStatusQuery = "SELECT status FROM Address WHERE addressId = ?";
        return this.jdbcTemplate.queryForObject(getAddressStatusQuery, String.class, addressId);
    }

    public String getUserStatus(int userId) {
        String getAddressStatusQuery = "SELECT status FROM User WHERE userId = ?";
        return this.jdbcTemplate.queryForObject(getAddressStatusQuery, String.class, userId);
    }
}
