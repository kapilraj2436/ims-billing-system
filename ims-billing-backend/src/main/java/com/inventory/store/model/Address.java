package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Address {

    private int addressId;
    private String addressString1;
    private String addressString2;
    private City city;


    public Address(int customerAddressId) {
        this.addressId = customerAddressId;
    }
}
