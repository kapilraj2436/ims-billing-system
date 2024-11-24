package com.inventory.store.repository;

import com.inventory.store.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Component
public class PaymentStatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addPaymentStatus(PaymentStatus paymentStatus) {
        String insertQuery = "insert into inv_paymentstatus(paymentStatusName, description)" +
                " values (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paymentStatus.getPaymentStatusName());
            ps.setString(2, paymentStatus.getDescription());
            return ps;
        }, keyHolder);

        // Add the generated paymentId to the list
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public PaymentStatus getPaymentStatusById(int paymentStatusId) {
        String selectQuery = "select * from inv_paymentstatus where paymentStatusId=?";
        return jdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<>(PaymentStatus.class), paymentStatusId);
    }
}
