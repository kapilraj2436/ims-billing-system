package com.inventory.store.repository;

import com.inventory.store.model.BankDetails;
import com.inventory.store.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Component
public class PaymentMethodRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addPaymentMethod(PaymentMethod paymentMethod) {
        String insertQuery = "insert into inv_paymentMethod(paymentMethod, bankDetailsId, description)" + " values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paymentMethod.getPaymentMethod());
            ps.setInt(2, paymentMethod.getBankDetails().getBankDetailsId());
            ps.setString(3, paymentMethod.getDescription());
            ;
            return ps;
        }, keyHolder);

        // Add the generated paymentId to the list
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        String selectQuery = "select * from inv_paymentMethod where paymentMethodId=?";
        return jdbcTemplate.queryForObject(selectQuery, (rs, rowNum) -> {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setPaymentMethodId(rs.getInt("paymentMethodId"));
            paymentMethod.setPaymentMethod(rs.getString("paymentMethod"));
            paymentMethod.setDescription(rs.getString("description"));
            paymentMethod.setBankDetails(new BankDetails(rs.getInt("bankDetailsId")));
            return paymentMethod;
        }, paymentMethodId);
    }
}
