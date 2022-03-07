package com.example.demo.src.review.model.response;

import lombok.*;

@Data
@AllArgsConstructor
public class GetReviewRes {
    private int reviewId;
    private String restaurantName;
    private String myReview;
    private String imageUrl;
    private int star;
    private String isOwner;
    private String myUpdatedAt;
    private String ownerReview;
    private String ownerUpdatedAt;
}
