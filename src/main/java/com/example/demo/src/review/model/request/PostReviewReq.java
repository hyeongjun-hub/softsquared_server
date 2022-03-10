package com.example.demo.src.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewReq {
    private String content;
    private String isPrivate;
    private String imageUrl;
    private int star;
    private int reviewId;
}
