package com.example.demo.src.restaurant;

import com.example.demo.src.restaurant.model.response.GetRestaurantDetailRes;
import com.example.demo.src.restaurant.model.response.GetRestaurantRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetRestaurantRes> getRestaurants(int categoryId) {
        String getRestaurantsQuery = "select categoryId, Restaurant.restaurantId as restaurantId, Restaurant.restaurantName as restaurantName, Count(star) reviewCount, AVG(star) reviewAvg, avatarUrl, restaurantName, deliveryStart, deliveryEnd, deliveryMinMoney, deliveryTipMin, deliveryTipMax, orderMethod from Restaurant left join Review R on Restaurant.restaurantId = R.restaurantId where categoryId = ? group by Restaurant.restaurantId";
        int getRestaurantParams = categoryId;
        return this.jdbcTemplate.query(getRestaurantsQuery,
                (rs, rowNum) -> new GetRestaurantRes(
                        rs.getInt("categoryId"),
                        rs.getInt("restaurantId"),
                        rs.getString("restaurantName"),
                        rs.getInt("reviewCount"),
                        rs.getDouble("reviewAvg"),
                        rs.getString("avatarUrl"),
                        rs.getInt("deliveryMinMoney"),
                        rs.getInt("deliveryStart"),
                        rs.getInt("deliveryEnd"),
                        rs.getInt("deliveryTipMin"),
                        rs.getInt("deliveryTipMax"),
                        rs.getString("orderMethod")
                ), getRestaurantParams
        );
    }

    public List<GetRestaurantDetailRes> getRestaurantDetail(int restaurantId){
        String getRestaurantDetailQuery = "select Restaurant.restaurantId, restaurantName, url, clean, deliveryMinMoney, paymentMethod,deliveryStart, deliveryEnd, deliveryTipMin, deliveryTipMax, takeOutMinMoney, orderMethod, cookingTimeStart, cookingTimeEnd, restaurantAddress, paymentMethodTO, M.menuDetail, bigMenuName, menuName, popular, menuSummary, menuImageUrl, price from Restaurant inner join BigMenu BM on Restaurant.restaurantId = BM.restaurantId inner join Menu M on BM.bigMenuId = M.bigMenuId left join RestaurantProfileImage RPI on Restaurant.restaurantId = RPI.restaurantId where Restaurant.restaurantId = ?";
        int getRestaurantDetailParams = restaurantId;
        return this.jdbcTemplate.query(getRestaurantDetailQuery,
                (rs, rowNum) -> new GetRestaurantDetailRes(
                        rs.getInt("restaurantId"),
                        rs.getString("restaurantName"),
                        rs.getString("url"),
                        rs.getString("clean"),
                        rs.getInt("deliveryMinMoney"),
                        rs.getString("paymentMethod"),
                        rs.getInt("deliveryStart"),
                        rs.getInt("deliveryEnd"),
                        rs.getInt("deliveryTipMin"),
                        rs.getInt("deliveryTipMax"),
                        rs.getInt("takeOutMinMoney"),
                        rs.getString("orderMethod"),
                        rs.getInt("cookingTimeStart"),
                        rs.getInt("cookingTimeEnd"),
                        rs.getString("restaurantAddress"),
                        rs.getString("paymentMethodTO"),
                        rs.getString("menuDetail"),
                        rs.getString("bigMenuName"),
                        rs.getString("menuName"),
                        rs.getString("popular"),
                        rs.getString("menuSummary"),
                        rs.getString("menuImageUrl"),
                        rs.getInt("price")
                ), getRestaurantDetailParams
        );
    }

}
