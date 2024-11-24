package com.inventory.store.repository;

import com.inventory.store.model.PaymentMethod;
import com.inventory.store.model.PaymentStatus;
import com.inventory.store.model.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PaymentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void addOrderPayments(int orderId, List<Integer> paymentIds) {
        String sql = "INSERT INTO INV_Order_Payments (orderId, paymentId) VALUES (?, ?)";

        // Batch update to insert multiple productId entries for the given orderId
        jdbcTemplate.batchUpdate(sql, paymentIds, paymentIds.size(), (ps, paymentId) -> {
            ps.setInt(1, orderId);  // Set the orderId for each row
            ps.setInt(2, paymentId);  // Set the paymentId for each row
        });
    }

    public List<Payments> getPaymentsByOrderId(int orderId) {
        String sql = "SELECT paymentId FROM INV_Order_Payments where orderId = ?";
        List<Integer> paymentsIds = jdbcTemplate.queryForList(sql, Integer.class, orderId);
        return getPaymentByPaymentsIds(paymentsIds);
    }

    private List<Payments> getPaymentByPaymentsIds(List<Integer> paymentsIds) {
        String sql = "SELECT * FROM INV_PAYMENTS WHERE paymentId IN (:paymentsIds)";

        // Define named parameters and bind the paymentIds list
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("paymentsIds", paymentsIds);

        // Execute query and map results to Payments objects
        return namedParameterJdbcTemplate.query(sql, parameters, (rs, rowNum) -> {
            Payments payments = new Payments();
            payments.setPaymentId(rs.getInt("paymentId"));
            payments.setAmount(rs.getString("amount"));
            payments.setInvoiceId(rs.getString("invoiceId"));
            payments.setTransactionId(rs.getString("transactionId"));
            payments.setPaymentReceivedBy(rs.getString("paymentReceivedBy"));
            payments.setPaymentDate(rs.getString("paymentDate"));
            payments.setPaymentStatus(new PaymentStatus(rs.getInt("paymentStatusId")));
            payments.setPaymentMethod(new PaymentMethod(rs.getInt("paymentMethodId")));
            return payments;
        });

    }

    public List<Integer> addPayments(List<Payments> payments) {
        String sql = "INSERT INTO INV_PAYMENTS (amount, paymentDate, invoiceId, paymentMethodId, paymentStatusId, " +
                "paymentReceivedBy, transactionId) " +
                "VALUES (?, SYSDATE(), ?, ?, ?, ?, ?)";

        List<Integer> paymentIds = new ArrayList<>();

        for (Payments payment : payments) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, payment.getAmount());
                ps.setString(2, payment.getInvoiceId());
                ps.setInt(3, payment.getPaymentMethod().getPaymentMethodId());
                ps.setInt(4, payment.getPaymentStatus().getPaymentStatusId());
                ps.setString(5, payment.getPaymentReceivedBy());
                ps.setString(6, payment.getTransactionId());
                return ps;
            }, keyHolder);

            // Add the generated paymentId to the list
            paymentIds.add(Objects.requireNonNull(keyHolder.getKey()).intValue());
        }

        return paymentIds;
    }

    public int addPayments(Payments payment) {
        String sql = "INSERT INTO INV_PAYMENTS (amount, paymentDate, invoiceId, paymentMethodId, paymentStatusId, " +
                "paymentReceivedBy, transactionId) " +
                "VALUES (?, SYSDATE(), ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, payment.getAmount());
            ps.setString(2, payment.getInvoiceId());
            ps.setInt(3, payment.getPaymentMethod().getPaymentMethodId());
            ps.setInt(4, payment.getPaymentStatus().getPaymentStatusId());
            ps.setString(5, payment.getPaymentReceivedBy());
            ps.setString(6, payment.getTransactionId());
            return ps;
        }, keyHolder);

        // Add the generated paymentId to the list
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<Payments> getAllPayments() {
        String sql = "SELECT paymentId, amount, paymentDate, invoiceId, paymentMethodId, paymentStatusId, " +
                "paymentReceivedBy, transactionId FROM INV_PAYMENTS";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Payments payments = new Payments();
            payments.setPaymentId(rs.getInt("paymentId"));
            payments.setPaymentStatus(new PaymentStatus(rs.getInt("paymentStatusId")));
            payments.setPaymentMethod(new PaymentMethod(rs.getInt("paymentMethodId")));
            payments.setPaymentDate(rs.getString("paymentDate"));
            payments.setTransactionId(rs.getString("transactionId"));
            payments.setInvoiceId(rs.getString("invoiceId"));
            payments.setPaymentReceivedBy(rs.getString("paymentReceivedBy"));
            payments.setAmount(rs.getString("amount"));
            return payments;
        });
    }

    public Payments getPaymentsByPaymentId(int paymentId) {
        String sql = "SELECT paymentId, amount, paymentDate, invoiceId, paymentMethodId, paymentStatusId, " +
                "paymentReceivedBy, transactionId FROM INV_PAYMENTS WHERE paymentId = ? ";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Payments payments = new Payments();
                payments.setPaymentId(rs.getInt("paymentId"));
                payments.setAmount(rs.getString("amount"));
                payments.setInvoiceId(rs.getString("invoiceId"));
                payments.setTransactionId(rs.getString("transactionId"));
                payments.setPaymentReceivedBy(rs.getString("paymentReceivedBy"));
                payments.setPaymentDate(rs.getString("paymentDate"));
                payments.setPaymentStatus(new PaymentStatus(rs.getInt("paymentStatusId")));
                payments.setPaymentMethod(new PaymentMethod(rs.getInt("paymentMethodId")));
                return payments;
        }, paymentId);
    }
}
