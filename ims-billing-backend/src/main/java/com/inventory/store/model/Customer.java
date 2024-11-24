package com.inventory.store.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Customer {

    private int customerId;
    private String customerName;
    private String customerMobile;
    private Address customerAddress;
    private String gstinNumber;

    public Customer() {
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }
}
