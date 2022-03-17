package com.example.demo.src.user.model.entity;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    @NotBlank(message = "이름을 입력하세요.")
    private String userName;
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
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
