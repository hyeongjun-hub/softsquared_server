package com.example.demo.src.user.model.entity;

import lombok.*;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userName;

    @Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
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
