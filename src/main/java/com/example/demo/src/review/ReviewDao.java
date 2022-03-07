package com.example.demo.src.review;

import com.example.demo.src.review.model.request.PatchReviewReq;
import com.example.demo.src.review.model.request.PostReviewReq;
import com.example.demo.src.review.model.response.GetReviewRes;
import com.example.demo.src.review.model.response.PostReviewRes;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetReviewRes> getMyReviews(int userId) {
        String getMyReviewsQuery = "select Review.reviewId, restaurantName, Review.content myReview, Review.imageUrl imageUrl, Review.star star, Review.isOwner isOwner, Review.updatedAt myUpdatedAt, PR.content ownerReview, PR.updatedAt ownerUpdatedAt from Review inner join OrderList OL on Review.orderListId = OL.orderListId inner join UserCart UC on OL.userCartID = UC.userCartId left join Restaurant R on Review.restaurantId = R.restaurantId left join Review PR on Review.reviewId = PR.postReviewId where userId = ?";
        int getMyReviewsParams = userId;
        return this.jdbcTemplate.query(getMyReviewsQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9))
                , getMyReviewsParams);
    }

    public PostReviewRes createReview(int orderListId, int restaurantId, PostReviewReq postReviewReq) {
        String createReviewQuery = "INSERT INTO Review (content, isPrivate, imageUrl, star, orderListId, restaurantId) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] createReviewParams = new Object[]{postReviewReq.getContent(), postReviewReq.getIsPrivate(), postReviewReq.getImageUrl(), postReviewReq.getStar(), orderListId, restaurantId};
        System.out.println("createReviewQuery = " + createReviewQuery);
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        int reviewId = this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
        String content = postReviewReq.getContent();
        return new PostReviewRes(reviewId, content);

    }

    public PostReviewRes createOwnerReview(int reviewId, String content) {
        String createOwnerReviewQuery = "insert into Review (content, isOwner, postReviewId) values(?, 'Y', ?)";
        Object[] creatOwnerReviewParams = new Object[]{content, reviewId};
        this.jdbcTemplate.update(createOwnerReviewQuery, creatOwnerReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        int ownerReviewId = this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
        return new PostReviewRes(ownerReviewId, content);
    }

    public void delReview(int reviewId) {
        String delReviewQuery = "Update Review set status = 'D' where reviewId = ?";
        int delReviewParams = reviewId;
        this.jdbcTemplate.update(delReviewQuery, delReviewParams);
    }

    public void editReview(int reviewId, PatchReviewReq patchReviewReq) {
        String editReviewQuery = "UPDATE Review t SET t.content = ?, t.isPrivate = ?, t.imageUrl = ?, t.star = ? WHERE t.reviewId = ?";
        Object[] editReviewParams = new Object[]{patchReviewReq.getContent(), patchReviewReq.getIsPrivate(), patchReviewReq.getImageUrl(), patchReviewReq.getStar(), reviewId};
        this.jdbcTemplate.update(editReviewQuery, editReviewParams);
    }

}
