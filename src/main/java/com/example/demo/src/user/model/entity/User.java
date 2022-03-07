package com.example.demo.src.user.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
