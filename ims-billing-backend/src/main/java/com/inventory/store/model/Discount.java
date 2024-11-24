package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Discount {

    private int discountId;
    private String discountName;
    private String discountValue;
    private String discountDescription;


    public Discount(int discountId) {
        this.discountId = discountId;
    }
}
