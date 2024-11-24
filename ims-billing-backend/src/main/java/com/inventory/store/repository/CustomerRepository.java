package com.inventory.store.repository;

import com.inventory.store.model.Address;
import com.inventory.store.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int addCustomer(Customer customer) {
        String insertQuery = "INSERT INTO INV_CUSTOMER (customerName, customerMobile, gstinNumber, customerAddressId) " +
                "VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Use jdbcTemplate update method with KeyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"customerId"});
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerMobile());
            ps.setString(3, customer.getGstinNumber());
            ps.setInt(4, customer.getCustomerAddress().getAddressId());
            return ps;
        }, keyHolder);

        // Return the generated key (customerId)
        return Objects.requireNonNull(keyHolder.getKey()).intValue();


    }

    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM INV_CUSTOMER where customerId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerId"));
            customer.setCustomerMobile(rs.getString("customerMobile"));
            customer.setCustomerName(rs.getString("customerName"));
            customer.setGstinNumber(rs.getString("gstinNumber"));
            customer.setCustomerAddress(new Address(rs.getInt("customerAddressId")));
            return customer;
        }, customerId);
    }

    public int addCustomerAddress(int customerId, List<Integer> customerAddress) {
        for (Integer addressId : customerAddress) {
            String insertQuery = "insert into INV_CUSTOMER_ADDRESS(customerId, addressId) values (?,?)";
            jdbcTemplate.update(insertQuery, customerId, addressId);
        }
        return customerAddress.size();
    }


    public List<Integer> getCustomerAddress(int customerId) {
        String selectSql = "SELECT addressId from INV_CUSTOMER_ADDRESS where customerId = ?";
        return jdbcTemplate.queryForList(selectSql, Integer.class, customerId);
    }

    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM INV_CUSTOMER";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerId"));
            customer.setCustomerMobile(rs.getString("customerMobile"));
            customer.setCustomerName(rs.getString("customerName"));
            customer.setGstinNumber(rs.getString("gstinNumber"));
            customer.setCustomerAddress(new Address(rs.getInt("customerAddressId")));
            return customer;
        });
    }
}



