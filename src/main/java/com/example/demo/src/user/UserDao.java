package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getString("baeminPayPassword"),
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
                        rs.getString("baeminPayPassword"),
                        rs.getInt("point"),
                        rs.getString("mailAccept"),
                        rs.getString("smsAccept"),
                        rs.getString("grade"),
                        rs.getString("status")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userId){
        String getUserQuery = "select * from User where userId = ?";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getString("baeminPayPassword"),
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
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set userName = ? where userId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public int delUser(PostUserDelReq postUserDelReq){
        String deleteUserQuery = "update User set status = 'D' where userId = ?";
        int deleteUserParams = postUserDelReq.getUserId();
        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userId, password, userEmail, userName from User where userEmail = ?";
        String getPwdParams = postLoginReq.getUserEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getString("profileImageUrl"),
                        rs.getString("phoneNumber"),
                        rs.getString("baeminPayPassword"),
                        rs.getInt("point"),
                        rs.getString("mailAccept"),
                        rs.getString("smsAccept"),
                        rs.getString("grade"),
                        rs.getString("status")
                ),
                getPwdParams
                );

    }


}
