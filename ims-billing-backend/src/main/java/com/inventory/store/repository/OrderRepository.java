package com.inventory.store.repository;

import com.inventory.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addOrder(Order order) {
        String insertQuery = "INSERT INTO INV_ORDER (customerId, hsnCode, orderPrice, discountId, paymentId) VALUES (?, ?, ?, ?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Use jdbcTemplate update method with KeyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"orderId"});
            ps.setInt(1, order.getCustomer().getCustomerId());
            ps.setString(2, order.getHsnCode());
            ps.setString(3, order.getOrderPrice());
            ps.setInt(4, order.getDiscount().getDiscountId());
            ps.setInt(5, order.getPayment().getPaymentId());
            return ps;
        }, keyHolder);

        // Return the generated key (customerId)
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM INV_ORDER WHERE orderId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerId")); // Implement this method accordingly
            order.setCustomer(customer);
            order.setHsnCode(rs.getString("hsnCode"));
            order.setOrderPrice(rs.getString("orderPrice"));

            order.setProduct(getProductsByOrderId(orderId)); // Get products associated with this order
            order.setPayment(getPaymentsByOrderId(orderId).getFirst()); // Get payments associated with this order
            order.setDiscount(getDiscountsByOrderId(orderId).getFirst()); // Get discounts associated with this order

            return order;
        }, orderId);
    }

    public List<Product> getProductsByOrderId(int orderId) {
        String sql = "SELECT productId FROM INV_Order_Product WHERE orderId = ?";
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), orderId);
        List<Product> productDetails = new ArrayList<>();
        for (Product product : products) {
            sql = "SELECT * FROM INV_PRODUCT WHERE productId = ?";
            productDetails.add(jdbcTemplate.queryForObject
                    (sql, BeanPropertyRowMapper.newInstance(Product.class), product.getProductId()));
        }
        return productDetails;
    }

    public List<Payments> getPaymentsByOrderId(int orderId) {
        String sql = "SELECT paymentId FROM INV_Order_Payments WHERE orderId = ?";
        List<Payments> payments = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payments.class), orderId);
        List<Payments> paymentDetails = new ArrayList<>();
        for (Payments payment : payments) {
            sql = "SELECT * FROM INV_Payments WHERE paymentId = ?";
            paymentDetails.add(jdbcTemplate.queryForObject
                    (sql, BeanPropertyRowMapper.newInstance(Payments.class), payment.getPaymentId()));
        }
        return paymentDetails;
    }

    public List<Discount> getDiscountsByOrderId(int orderId) {
        String sql = "SELECT discountId FROM INV_Order_Discount WHERE orderId = ?";
        List<Discount> discounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Discount.class), orderId);
        List<Discount> discountDetails = new ArrayList<>();
        for (Discount discount : discounts) {
            sql = "SELECT * FROM INV_DISCOUNT WHERE discountId = ?";
            discountDetails.add(jdbcTemplate.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Discount.class), discount.getDiscountId()));
        }
        return discountDetails;
    }

    private void addProductsToOrder(int orderId, List<Product> products) {
        String sql = "INSERT INTO INV_Order_Product (orderId, productId) VALUES (?, ?)";
        for (Product product : products) {
            jdbcTemplate.update(sql, orderId, product.getProductId());
        }
    }

    // Method to add discounts associated with the order
    private void addDiscountsToOrder(int orderId, List<Discount> discounts) {
        String sql = "INSERT INTO Order_Discounts (orderId, discountId) VALUES (?, ?)";
        for (Discount discount : discounts) {
            jdbcTemplate.update(sql, orderId, discount.getDiscountId());
        }
    }

    public int updateOrder(Order order) {
        String updateQuery = "update INV_ORDER set id = ?, name = ?, contact_number = ?, order = ? where orderId = ?";
        return jdbcTemplate.update(updateQuery, order.getOrderId(), order.getOrderPrice(),
                order.getCustomer(), order.getDiscount(), order.getPayment(), order.getHsnCode(), order.getProduct());
    }

    public int deleteOrderById(int id) {
        String deleteByIdQuery = "delete from INV_ORDER where id = ?";
        return jdbcTemplate.update(deleteByIdQuery, id);
    }

    public List<Order> getAllOrders() {
        String selectAllQuery = "SELECT * FROM INV_ORDER";
        return jdbcTemplate.query(selectAllQuery, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setOrderPrice(rs.getString("orderPrice"));
            order.setHsnCode(rs.getString("hsnCode"));
            order.setCustomer(new Customer(rs.getInt("customerId")));
            order.setDiscount(new Discount(rs.getInt("discountId")));
            order.setPayment(new Payments(rs.getInt("paymentId")));
            return order;
        });


    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        String selectAllQuery = "SELECT * FROM INV_ORDER WHERE customerId=?";
        return jdbcTemplate.query(selectAllQuery, new BeanPropertyRowMapper<>(Order.class), customerId);
    }
}
