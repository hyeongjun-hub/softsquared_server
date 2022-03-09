package com.example.demo.src.review;

import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import com.example.demo.src.review.model.response.PostReviewRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
    List<GetReviewRes> getMyReviews(int userId);

    int createReview(int orderListId, int restaurantId, PostReviewReq postReviewReq);

    int createOwnerReview(int reviewId, String content);

    void delReview(int reviewId);

    void editReview(int reviewId, PatchReviewReq patchReviewReq);
}
