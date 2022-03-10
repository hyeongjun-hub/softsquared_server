package com.example.demo.src.cart;

import com.example.demo.src.cart.model.response.GetCartRes;
import com.example.demo.src.cart.model.request.PostAddAdditionalCartReq;
import com.example.demo.src.cart.model.request.PostAddCartReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //POST
    public int createCart(int userId) {
        String createCartQuery = "insert into UserCart (userId) values (?)";
        int createCartParams = userId;
        this.jdbcTemplate.update(createCartQuery, createCartParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    //GET
    public List<GetCartRes> getCart(int userCartId) {
        String getCartQuery = "select UserCart.userCartId, restaurantName, menuImageUrl, menuName, M.price as menuPrice, additionalMenuName, AM.price as additionalMenuPrice, OD.amount, priceSum from UserCart inner join User U on UserCart.userId = U.userId inner join OrderDetail OD on UserCart.userCartId = OD.userCartId left join Menu M on OD.menuId = M.menuId left join AdditionalMenu AM on OD.additionalMenuId = AM.additionalMenuId left join BigMenu BM on M.bigMenuId = BM.bigMenuId left Join Restaurant R on BM.restaurantId = R.restaurantId where UserCart.userCartId = ? and UserCart.status = 'Y'";
        int getCartParams = userCartId;
        return this.jdbcTemplate.query(getCartQuery,
                (rs, rowNum) -> new GetCartRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9)
                ), getCartParams
        );
    }

    //POST
    public int addMenu(int userCartId, PostAddCartReq postAddCartReq) {
        String addMenuQuery = "insert into OrderDetail (userCartId, menuId, amount) values (?, ?, ?)";
        Object[] addMenuParams = new Object[]{userCartId, postAddCartReq.getMenuId(), postAddCartReq.getAmount()};
        this.jdbcTemplate.update(addMenuQuery, addMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int calculatePrice(int orderDetailId){
        String calculationQuery = "select M.price * OrderDetail.amount from OrderDetail left join Menu M on OrderDetail.menuId = M.menuId where orderDetailId = ?";
        return this.jdbcTemplate.queryForObject(calculationQuery, int.class, orderDetailId);
    }

    public void updateCart(int userCartId, int priceSum) {
        String updateCartQuery = "Update UserCart set priceSum = priceSum + ? where userCartId = ?";
        Object[] updateCartParams = new Object[]{priceSum, userCartId};
        this.jdbcTemplate.update(updateCartQuery, updateCartParams);
    }

    public int calculateAdditionalPrice(int orderDetailId){
        String calculationAPQuery = "select AM.price * OrderDetail.amount from OrderDetail left join AdditionalMenu AM on OrderDetail.additionalMenuId = AM.additionalMenuId where orderDetailId = ? and OrderDetail.status = 'Y'";
        return this.jdbcTemplate.queryForObject(calculationAPQuery, int.class, orderDetailId);
    }

    public void updateCartAdditional(int userCartId, int additionalPriceSum) {
        String updateCartQuery = "Update UserCart set priceSum = priceSum + ? where userCartId = ?";
        Object[] updateCartParams = new Object[]{additionalPriceSum, userCartId};
        this.jdbcTemplate.update(updateCartQuery, updateCartParams);
    }

    public int addAdditionalMenu(int userCartId, PostAddAdditionalCartReq postAddAdditionalCartReq) {
        String addAdditionalMenuQuery = "insert into OrderDetail (userCartId, additionalMenuId, amount) values (?, ?, ?)";
        Object[] addAdditionalMenuParams = new Object[]{userCartId, postAddAdditionalCartReq.getAdditionalMenuId(), postAddAdditionalCartReq.getAmount()};
        this.jdbcTemplate.update(addAdditionalMenuQuery, addAdditionalMenuParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public void delCart(int userCartId) {
        String delCartQuery = "Update UserCart set status = 'D' where userCartId = ?";
        int delCartParams = userCartId;
        this.jdbcTemplate.update(delCartQuery, delCartParams);
    }

}

