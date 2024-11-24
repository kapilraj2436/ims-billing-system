package com.inventory.store.repository;

import com.inventory.store.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Repository
public class DiscountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addOrderDiscount(int orderId, List<Integer> discountIds) {
        String sql = "INSERT INTO INV_Order_Discount (orderId, discountId) VALUES (?, ?)";

        // Batch update to insert multiple productId entries for the given orderId
        jdbcTemplate.batchUpdate(sql, discountIds, discountIds.size(), (ps, discountId) -> {
            ps.setInt(1, orderId);  // Set the orderId for each row
            ps.setInt(2, discountId);  // Set the discountId for each row
        });


    }

    public List<Discount> getDiscountByOrderId(int orderId) {
        String sql = "SELECT discountId FROM INV_DISCOUNT where orderId = ?";
        List<Integer> discountIds = jdbcTemplate.queryForList(sql, Integer.class, orderId);
        return getDiscountByOrderId(discountIds);
    }

    private List<Discount> getDiscountByOrderId(List<Integer> discountIds) {
        String inSql = discountIds.stream()
                .map(id -> "?").collect(Collectors.joining(","));
        String sql = String.format("SELECT discountName, discountValue, discountDescription" +
                " FROM INV_DISCOUNT WHERE discountId IN (%s)", inSql);

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Discount.class), discountIds.toArray());
    }

    public void addDiscount(Discount discount) {
        String insertSql = "INSERT INTO INV_Discount (discountName, discountValue, discountDescription) VALUES (?,?,?)";
        jdbcTemplate.update(insertSql, discount.getDiscountName(), discount.getDiscountValue(), discount.getDiscountDescription());
    }

    public List<Discount> getAllDiscounts() {
        String sql = "SELECT discountId, discountName, discountValue, discountDescription FROM INV_DISCOUNT";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Discount.class));
    }

    public Discount getDiscountByDiscountId(int discountId) {
        String sql = "SELECT discountId, discountName, discountValue, discountDescription FROM INV_DISCOUNT " +
                "WHERE discountId=?";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Discount.class), discountId);
    }
}
