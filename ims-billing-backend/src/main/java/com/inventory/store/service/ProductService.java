package com.inventory.store.service;

import com.inventory.store.model.Product;
import com.inventory.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProduct(int productId) {
        return productRepository.getProductById(productId);
    }

    public void updateProduct(int productId, Product product) {
        Product dbProduct = productRepository.getProductById(productId);

        dbProduct.setProductDescription(product.getProductDescription());
        dbProduct.setProductName(product.getProductName());
        dbProduct.setAvailableQuantity(product.getAvailableQuantity());
        dbProduct.setPricePerUnit(product.getPricePerUnit());

        productRepository.updateProduct(dbProduct);
    }

    public void addOrderProduct(int orderId, List<Product> product) {
        //List<Integer> productIds = product.stream().map(Product::getProductId).toList();
        productRepository.addOrderProduct(orderId, product);
    }

    public List<Product> getProductByOrderId(int orderId) {
        return productRepository.getProductByOrderId(orderId);

    }

    public Product getProductByProductId(int productId) {
        return productRepository.getProductByProductId(productId);
    }
}
