package com.example.demo.src.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewRes {
    private int reviewId;
    private String content;
}
