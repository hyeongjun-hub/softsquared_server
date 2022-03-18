package com.example.demo.src.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOwnerReviewReq {
    @NotBlank(message = "리뷰 내용을 입력하세요.")
    private String content;
    private int ownerReviewId;
}
