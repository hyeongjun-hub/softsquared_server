package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostOwnerReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import com.example.demo.src.review.model.response.PostReviewRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;
    private final JwtService jwtService;

    /**
     * 회원 리뷰 조회 API
     * 자신이 작성한 리뷰들을 조회
     *
     * @return BaseResponse<List < GetReviewRes>>
     */
    @GetMapping("")
    public BaseResponse<List<GetReviewRes>> getMyReviews() throws BaseException {
        int userId = jwtService.getUserId();
        List<GetReviewRes> getReviewRes = reviewProvider.getMyReviews(userId);
        return new BaseResponse<>(getReviewRes);
    }

    /**
     * 리뷰 작성 API
     *
     * @param orderListId
     * @param restaurantId
     * @param postReviewReq
     * @return BaseResponse<PostReviewRes>
     */
    @PostMapping("/{orderListId}/{restaurantId}")
    public BaseResponse<PostReviewRes> createReview(@PathVariable("orderListId") int orderListId, @PathVariable("restaurantId") int restaurantId, @RequestBody PostReviewReq postReviewReq) throws BaseException {
        PostReviewRes postReviewRes = reviewService.createReview(orderListId, restaurantId, postReviewReq);
        return new BaseResponse<>(postReviewRes);
    }

    /**
     * 사장님 리뷰 작성 API
     *
     * @param reviewId
     * @param postOwnerReviewReq
     * @return BaseResponse<PostReviewRes>
     */
    @PostMapping("/{reviewId}/owner")
    public BaseResponse<PostReviewRes> createOwnerReview(@PathVariable("reviewId") int reviewId, @RequestBody PostOwnerReviewReq postOwnerReviewReq) throws BaseException {
        PostReviewRes ownerReview = reviewService.createOwnerReview(reviewId, postOwnerReviewReq);
        return new BaseResponse<>(ownerReview);
    }

    /**
     * 리뷰 삭제 API
     *
     * @param reviewId
     * @return BaseResponse<String>
     */
    @PatchMapping("/{reviewId}/delete")
    public BaseResponse<String> delReview(@PathVariable("reviewId") int reviewId) throws BaseException {
        reviewService.delReview(reviewId);
        return new BaseResponse<>("");
    }

    /**
     * 리뷰 수정 API
     *
     * @param reviewId
     * @param patchReviewReq
     * @return BaseResponse<String>
     */
    @PatchMapping("/{reviewId}")
    public BaseResponse<String> editReview(@PathVariable("reviewId") int reviewId, @RequestBody PatchReviewReq patchReviewReq) throws BaseException {
        reviewService.editReview(reviewId, patchReviewReq);
        return new BaseResponse<>("");
    }
}
