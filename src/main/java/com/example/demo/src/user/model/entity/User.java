package com.example.demo.src.user.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

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
    @NotBlank(message = "전화번호를 입력하세요")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호 형식을 확인해주세요.")
    private String phoneNumber;
    @PositiveOrZero(message = "0 또는 양수만 가능")
    private int point;
    private String mailAccept;
    private String smsAccept;
    private String grade;
    private String status;
}
