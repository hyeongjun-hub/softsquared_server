package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostOwnerReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;

    public PostReviewRes createReview(int orderListId, int restaurantId, PostReviewReq postReviewReq) throws BaseException {
        try{
            return reviewDao.createReview(orderListId, restaurantId, postReviewReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostReviewRes createOwnerReview(int reviewId, String content) throws BaseException {
        try {
            return reviewDao.createOwnerReview(reviewId, content);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void delReview(int reviewId) throws BaseException {
        try{
            reviewDao.delReview(reviewId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editReview(int reviewId, PatchReviewReq patchReviewReq) throws BaseException {
        try{
            reviewDao.editReview(reviewId, patchReviewReq);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}