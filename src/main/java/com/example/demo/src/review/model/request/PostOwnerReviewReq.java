package com.example.demo.src.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOwnerReviewReq {
    private String content;
    private int ownerReviewId;
}
