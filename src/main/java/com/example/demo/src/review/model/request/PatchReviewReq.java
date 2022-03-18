package com.example.demo.src.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchReviewReq {
    @NotBlank(message = "리뷰 내용을 입력하세요.")
    private String content;
    private String isPrivate;
    private String imageUrl;
    private int star;
}
