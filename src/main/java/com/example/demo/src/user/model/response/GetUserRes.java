package com.example.demo.src.user.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRes {
    private int userId;
    private String userName;
    private String userEmail;
    private String password;
    private String profileImageUrl;
    private String phoneNumber;
    private int point;
    private String mailAccept;
    private String smsAccept;
    private String grade;
    private String status;

}
