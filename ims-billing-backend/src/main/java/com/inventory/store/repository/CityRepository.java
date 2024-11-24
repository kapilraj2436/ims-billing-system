package com.inventory.store.repository;

import com.inventory.store.model.City;
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
public class CityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCity(City city) {
        String sql = "INSERT INTO INV_CITY(cityName, cityPinCode, cityDescription) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Use jdbcTemplate update method with KeyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"cityId"});
            ps.setString(1, city.getCityName());
            ps.setString(2, city.getCityPinCode());
            ps.setString(3, city.getCityDescription());
            return ps;
        }, keyHolder);

        // Return the generated key (customerId)
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<City> getCity() {
        String sql = "SELECT * FROM INV_CITY";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(City.class));
    }

    public City getCityById(int cityId) {
        String sql = "SELECT * FROM INV_CITY WHERE cityId=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(City.class), cityId);
    }


    public List<City> getAllCity() {
        String sql = "SELECT cityId, cityName, cityPinCode, cityDescription FROM INV_CITY";

        // Execute the query and return the result mapped to City objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(City.class));
    }
}
