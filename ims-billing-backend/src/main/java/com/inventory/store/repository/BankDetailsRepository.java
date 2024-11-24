package com.inventory.store.repository;

import com.inventory.store.model.BankDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
@Repository
public class BankDetailsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addBankDetails(BankDetails bankDetails) {
        String insertQuery = "insert into INV_BankDetails(bankName, accountNumber, ifscCode, branch, ownerName, description)" + " values (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bankDetails.getBankName());
            ps.setString(2, bankDetails.getAccountNumber());
            ps.setString(3, bankDetails.getIfscCode());
            ps.setString(4, bankDetails.getBranch());
            ps.setString(5, bankDetails.getOwnerName());
            ps.setString(6, bankDetails.getDescription());
            return ps;
        }, keyHolder);

        // Add the generated paymentId to the list
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public int updateBankDetails(BankDetails bankDetails) {
        String insertQuery = "update INV_BankDetails set bankName=?, accountNumber=?, ifscCode=?, branch=?," + " ownerName=?, description=?)";
        return jdbcTemplate.update(insertQuery, bankDetails.getBankName(), bankDetails.getAccountNumber(), bankDetails.getIfscCode(), bankDetails.getBranch(), bankDetails.getOwnerName(), bankDetails.getDescription());
    }

    public BankDetails getBankDetailsById(int bankDetailsId) {
        String selectQuery = "select * from INV_BankDetails where bankDetailsId=?";
        return jdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<>(BankDetails.class), bankDetailsId);
    }

    public List<BankDetails> getAllBankDetails() {
        String selectAllQuery = "select * from INV_BankDetails";
        return jdbcTemplate.query(selectAllQuery, new BeanPropertyRowMapper<>(BankDetails.class));
    }

}
