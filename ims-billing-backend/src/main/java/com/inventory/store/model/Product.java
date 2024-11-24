package com.inventory.store.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {

    private int productId;
    private String productName;
    private String productDescription;
    private double pricePerUnit;
    private int availableQuantity;
    private int orderQuantity;

}
