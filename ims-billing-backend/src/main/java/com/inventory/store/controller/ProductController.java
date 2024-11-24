package com.inventory.store.controller;

import com.inventory.store.common.CommonController;
import com.inventory.store.model.Product;
import com.inventory.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements CommonController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/search")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/search/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductByProductId(productId);
    }

    @PostMapping("/update/{productId}")
    public void updateProductById(@PathVariable int productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);

    }


}
