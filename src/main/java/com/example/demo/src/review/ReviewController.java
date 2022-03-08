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

import static com.example.demo.config.BaseResponseStatus.POST_REVIEWS_EMPTY_CONTENT;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;
    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<List<GetReviewRes>> getMyReviews() {
        try{
            int userId = jwtService.getUserId();
            List<GetReviewRes> getReviewRes = reviewProvider.getMyReviews(userId);
            return new BaseResponse<>(getReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{orderListId}/{restaurantId}")
    public BaseResponse<PostReviewRes> createReview(@PathVariable("orderListId") int orderListId, @PathVariable("restaurantId") int restaurantId, @RequestBody PostReviewReq postReviewReq) {
        if (postReviewReq.getContent() == null) {
            return new BaseResponse<>(POST_REVIEWS_EMPTY_CONTENT);
        }
        try{
            PostReviewRes postReviewRes = reviewService.createReview(orderListId, restaurantId, postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{reviewId}/owner")
    public BaseResponse<PostReviewRes> createOwnerReview(@PathVariable("reviewId") int reviewId, @RequestBody PostOwnerReviewReq postOwnerReviewReq) {
        try{
            PostReviewRes ownerReview = reviewService.createOwnerReview(reviewId, postOwnerReviewReq.getContent());
            return new BaseResponse<>(ownerReview);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/{reviewId}/delete")
    public BaseResponse<String> delReview(@PathVariable("reviewId") int reviewId) {
        try{
            reviewService.delReview(reviewId);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/{reviewId}")
    public BaseResponse<String> editReview(@PathVariable("reviewId") int reviewId, @RequestBody PatchReviewReq patchReviewReq) {
        try{
            reviewService.editReview(reviewId, patchReviewReq);
            return new BaseResponse<>("");
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
