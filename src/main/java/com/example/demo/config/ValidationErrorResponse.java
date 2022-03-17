package com.example.demo.config;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private boolean isSuccess;
    private int status;
    private String message;

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}