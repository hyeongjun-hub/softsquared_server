package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public PostReviewRes createReview(int orderListId, int restaurantId, PostReviewReq postReviewReq) throws BaseException {
        try{
            int reviewId = reviewMapper.createReview(orderListId, restaurantId, postReviewReq);
            return new PostReviewRes(reviewId, postReviewReq.getContent());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostReviewRes createOwnerReview(int reviewId, String content) throws BaseException {
        try {
            int ownerReviewId = reviewMapper.createOwnerReview(reviewId, content);
            return new PostReviewRes(ownerReviewId, content);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delReview(int reviewId) throws BaseException {
        try{
            reviewMapper.delReview(reviewId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editReview(int reviewId, PatchReviewReq patchReviewReq) throws BaseException {
        try{
            reviewMapper.editReview(reviewId, patchReviewReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}