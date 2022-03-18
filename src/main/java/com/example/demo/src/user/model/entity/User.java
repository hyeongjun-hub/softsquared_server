package com.example.demo.src.user.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 8, max = 20, message = "비밀번호 길이를 확인해주세요")
    private String password;
    private String profileImageUrl;
    private String phoneNumber;
    private int point;
    private String mailAccept;
    private String smsAccept;
    private String grade;
    private String status;
}
