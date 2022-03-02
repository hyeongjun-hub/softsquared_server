package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String password;
    private String profileImageUrl;
    private String phoneNumber;
    private String baeminPayPassword;
    private int point;
    private String mailAccept;
    private String smsAccept;
    private String grade;
    private String status;
}
