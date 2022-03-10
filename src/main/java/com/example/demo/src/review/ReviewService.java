package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostOwnerReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;

    @Transactional(rollbackFor = {BaseException.class})
    public PostReviewRes createReview(int orderListId, int restaurantId, PostReviewReq postReviewReq) throws BaseException {
        try{
            reviewMapper.createReview(orderListId, restaurantId, postReviewReq);
            int reviewId = postReviewReq.getReviewId();
            return new PostReviewRes(reviewId, postReviewReq.getContent());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
    public PostReviewRes createOwnerReview(int reviewId, PostOwnerReviewReq postOwnerReviewReq) throws BaseException {
        try {
            reviewMapper.createOwnerReview(reviewId, postOwnerReviewReq.getContent());
            int ownerReviewId = postOwnerReviewReq.getOwnerReviewId();
            return new PostReviewRes(ownerReviewId, postOwnerReviewReq.getContent());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(rollbackFor = {BaseException.class})
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