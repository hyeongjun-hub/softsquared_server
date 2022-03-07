package com.example.demo.src.order;

import com.example.demo.src.order.model.response.GetOrderDetailRes;
import com.example.demo.src.order.model.response.GetOrderRes;
import com.example.demo.src.order.model.request.PostOrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createOrder(PostOrderReq postOrderReq) {
        String createOrderQuery = "insert into OrderList (userCartId, request, toRider, usePoint, addressId, couponId, presentId, paymentMethodId) values (?,?,?,?,?,?,?,?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getUserCartId(), postOrderReq.getRequest(), postOrderReq.getToRider(), postOrderReq.getUsePoint(), postOrderReq.getAddressId(), postOrderReq.getCouponId(), postOrderReq.getPresentId(), postOrderReq.getPaymentMethodId()};
        this.jdbcTemplate.update(createOrderQuery, createOrderParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public void updatePrice(int orderListId) {
        String calculateQuery = "select GREATEST(Sum(priceSum) - IFNULL(amount,0) - IFNULL(price, 0), 0) as finalPrice from OrderList inner join UserCart UC on OrderList.userCartID = UC.userCartId left join Coupon C on OrderList.couponId = C.couponId left join RestaurantCoupon RC on C.restaurantCouponId = RC.restaurantCouponId left join Present P on OrderList.presentId = P.presentId where orderListId=?";
        int finalPrice = this.jdbcTemplate.queryForObject(calculateQuery, int.class, orderListId);
        String updatePriceQuery = "Update OrderList set finalPrice = ? where orderListId = ?";
        Object[] updatePriceParams = new Object[]{finalPrice, orderListId};
        this.jdbcTemplate.update(updatePriceQuery, updatePriceParams);
    }

    public List<GetOrderRes> getOrders(int userId) {
        String getOrdersQuery = "select distinct OrderList.orderListId as orderListId, avatarUrl, restaurantName, menuName, finalPrice, CASE WHEN TIMESTAMPDIFF(DAY, OrderList.createdAt, CURRENT_TIMESTAMP)<7 and TIMESTAMPDIFF(DAY, OrderList.createdAt, CURRENT_TIMESTAMP)>=1 THEN CONCAT(TIMESTAMPDIFF(DAY, OrderList.createdAt, CURRENT_TIMESTAMP), '일전') WHEN TIMESTAMPDIFF(DAY, OrderList.createdAt, CURRENT_TIMESTAMP)<1 THEN CONCAT(TIMESTAMPDIFF(HOUR, OrderList.createdAt, CURRENT_TIMESTAMP),'시간전') ELSE CONCAT(DATE_FORMAT(OrderList.createdAt, '%m/%d'), CASE WHEN DATE_FORMAT(OrderList.createdAt, '%w')=0 THEN ' (일)' WHEN DATE_FORMAT(OrderList.createdAt, '%w')=1 THEN ' (월)' WHEN DATE_FORMAT(OrderList.createdAt, '%w')=2 THEN ' (화)' WHEN DATE_FORMAT(OrderList.createdAt, '%w')=3 THEN ' (수)' WHEN DATE_FORMAT(OrderList.createdAt, '%w')=4 THEN ' (목)' WHEN DATE_FORMAT(OrderList.createdAt, '%w')=5 THEN ' (금)' ELSE ' (토)' END ) END as createdAt, OrderList.status from OrderList inner join UserCart UC on OrderList.userCartID = UC.userCartId inner join OrderDetail OD on UC.userCartId = OD.userCartId left join Menu M on OD.menuId = M.menuId left join AdditionalMenu AM on OD.additionalMenuId = AM.additionalMenuId inner join BigMenu BM on M.bigMenuId = BM.bigMenuId inner join Address A on OrderList.addressId = A.addressId inner join Restaurant R on BM.restaurantId = R.restaurantId inner join User U on UC.userId = U.userId where UC.userId = ?";
        int getOrdersParams = userId;
        return this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                ), getOrdersParams
        );
    }

    public List<GetOrderDetailRes> getOrder(int orderListId) {
        String getOrderQuery = "select distinct OrderList.orderListId as orderListId, (IfNULL(M.price,0) + IFNULL(AM.price, 0)) as price, OD.amount, restaurantName, menuName, additionalMenuName, finalPrice, DATE_FORMAT(OrderList.createdAt, '%Y년 %m월 %d일 %p %l:%s')as createdAt, deliveryAddress, restaurantPhoneNumber, request, toRider, spoon, address, phoneNumber from OrderList inner join PaymentMethod PM on OrderList.paymentMethodId = PM.paymentMethodId inner join UserCart UC on OrderList.userCartID = UC.userCartId inner join OrderDetail OD on UC.userCartId = OD.userCartId left join Menu M on OD.menuId = M.menuId left join AdditionalMenu AM on OD.additionalMenuId = AM.additionalMenuId left join BigMenu BM on M.bigMenuId = BM.bigMenuId inner join Address A on OrderList.addressId = A.addressId left join Restaurant R on BM.restaurantId = R.restaurantId inner join User U on UC.userId = U.userId where OrderListId = ?";
        int getOrderParams = orderListId;
        System.out.println("getOrderQuery = " + getOrderQuery);
        System.out.println("getOrderParams = " + getOrderParams);
        return this.jdbcTemplate.query(getOrderQuery,
                (rs, rowNum) -> new GetOrderDetailRes(
                        rs.getInt("orderListId"),
                        rs.getInt("price"),
                        rs.getInt("amount"),
                        rs.getString("restaurantName"),
                        rs.getString("menuName"),
                        rs.getString("additionalMenuName"),
                        rs.getInt("finalPrice"),
                        rs.getString("createdAt"),
                        rs.getString("restaurantPhoneNumber"),
                        rs.getString("request"),
                        rs.getString("toRider"),
                        rs.getString("spoon"),
                        rs.getString("address"),
                        rs.getString("phoneNumber")
                ), getOrderParams);
    }

}
