package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.response.GetReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@AllArgsConstructor
public class ReviewProvider {

    private final ReviewMapper reviewMapper;

    public List<GetReviewRes> getMyReviews(int userId) throws BaseException {
        try{
            List<GetReviewRes> getReviewRes = reviewMapper.getMyReviews(userId);
            return getReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
