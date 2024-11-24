package com.inventory.store.repository;

import com.inventory.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProduct(Product product) {
        String insertQuery = "insert into INV_PRODUCT(productName, productDescription, pricePerUnit, " +
                "availableQuantity) values (?,?,?,?)";
        jdbcTemplate.update(insertQuery, product.getProductName(), product.getProductDescription(),
                product.getPricePerUnit(), product.getAvailableQuantity());
    }

    public List<Product> getAllProducts() {
        String selectAllQuery = "select * from INV_PRODUCT";
        return jdbcTemplate.query(selectAllQuery, new BeanPropertyRowMapper<>(Product.class));
    }


    public Product getProductById(int productId) {
        String selectQuery = "select * from INV_PRODUCT where productId=?";
        return jdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<>(Product.class), productId);
    }


    public void updateProduct(Product dbProduct) {
        String updateQuery = "UPDATE INV_PRODUCT SET productName=?, productDescription=?," +
                " pricePerUnit=?, availableQuantity=? where productId=?";
        jdbcTemplate.update(updateQuery, dbProduct.getProductName(), dbProduct.getProductDescription(),
                dbProduct.getPricePerUnit(), dbProduct.getAvailableQuantity(), dbProduct.getProductId());
    }

    public void addOrderProduct(int orderId, List<Product> products) {
        String sql = "INSERT INTO INV_Order_Product (orderId, productId, quantity) VALUES (?, ?, ?)";

        // Batch update to insert multiple productId entries for the given orderId
        jdbcTemplate.batchUpdate(sql, products, products.size(), (ps, product) -> {
            ps.setInt(1, orderId);  // Set the orderId for each row
            ps.setInt(2, product.getProductId());  // Set the productId for each row
            ps.setInt(3, product.getOrderQuantity());
        });

    }

    public List<Product> getProductByOrderId(int orderId) {
        return getProductByProductIds(orderId);
    }

    private List<Product> getProductByProductIds(int orderId) {
//        String inSql = productIds.stream()
//                .map(id -> "?").collect(Collectors.joining(","));

        String sql = "select ip.productId as productId, ip.productName as productName, ip.productDescription as productDescription, " +
                " ip.pricePerUnit as pricePerUnit, iop.quantity as orderQuantity " +
                " from inv_product ip inner join INV_Order_Product iop on iop.productId=ip.productId " +
                "   inner join INV_Order io on iop.orderId=io.orderId where io.orderId=?";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), orderId);
    }

    public Product getProductByProductId(int productId) {
        String sql = "SELECT * FROM INV_PRODUCT WHERE productId =?";

        // Execute the query and return the result mapped to Product objects
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productId);
    }
}
