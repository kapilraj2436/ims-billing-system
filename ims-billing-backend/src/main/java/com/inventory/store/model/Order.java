package com.inventory.store.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Order {

    private int orderId;

    private Customer customer;
    private List<Product> product;
    private String hsnCode;
    private Discount discount;
    private String orderPrice;
    private Payments payment;

}
