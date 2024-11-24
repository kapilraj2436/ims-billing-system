package com.inventory.store.repository;

import com.inventory.store.model.Address;
import com.inventory.store.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Repository
public class AddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addAddress(Address address) {

        String insertQuery = "insert into INV_ADDRESS(addressString1, addressString2, cityId) " +
                "values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Use jdbcTemplate update method with KeyHolder
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"addressId"});
            ps.setString(1, address.getAddressString1());
            ps.setString(2, address.getAddressString2());
            ps.setInt(3, address.getCity().getCityId());
            return ps;
        }, keyHolder);

        // Return the generated key (customerId)
        return Objects.requireNonNull(keyHolder.getKey()).intValue();

    }

    public List<Address> getAllAddress() {
        String selectAllQuery = "select addressId, addressString1, addressString2, cityId from INV_Address";
        return jdbcTemplate.query(selectAllQuery, (rs, rowNum) -> {
            Address address = new Address();
            address.setAddressId(rs.getInt("addressId"));
            address.setAddressString1(rs.getString("addressString1"));
            address.setAddressString2(rs.getString("addressString2"));
            City city = new City();
            city.setCityId(rs.getInt("cityId"));
            address.setCity(city);
            return address;
        });
    }

    public Address getAddressById(int id) {
        String selectByIdQuery = "select addressId, addressString1, addressString2, cityId " +
                "from INV_Address where addressId = ?";

        return jdbcTemplate.queryForObject(selectByIdQuery, (rs, rowNum) -> {
            Address address = new Address();
            address.setAddressId(rs.getInt("addressId"));
            address.setAddressString1(rs.getString("addressString1"));
            address.setAddressString2(rs.getString("addressString2"));
            City city = new City();
            city.setCityId(rs.getInt("cityId"));
            address.setCity(city);
            return address;
        }, id);
    }

    public int updateAddress(Address address) {
        String updateQuery = "update INV_Address set addressId = ?, addressString1 = ?, addressString2 = ?, " +
                "cityId =? where addressId = ?";
        return jdbcTemplate.update(updateQuery, address.getAddressId(), address.getAddressString1(),
                address.getAddressString2(), address.getCity().getCityId());
    }

    public int deleteAddressById(int id) {
        String deleteByIdQuery = "delete from Address where addressId = ?";
        return jdbcTemplate.update(deleteByIdQuery, id);
    }

    public List<Address> getAllAddressByIds(List<Integer> addressIds) {
        String inSql = addressIds.stream()
                .map(id -> "?").collect(Collectors.joining(","));

        String selectAllQuery = String.format("SELECT * FROM INV_Address WHERE addressId IN (%s)", inSql);
        return jdbcTemplate.query(selectAllQuery, (rs, rowNum) -> {
            Address address = new Address();
            address.setAddressId(rs.getInt("addressId"));
            address.setAddressString1(rs.getString("addressString1"));
            address.setAddressString2(rs.getString("addressString2"));
            City city = new City();
            city.setCityId(rs.getInt("cityId"));
            address.setCity(city);
            return address;
        }, addressIds.toArray());
    }
}
