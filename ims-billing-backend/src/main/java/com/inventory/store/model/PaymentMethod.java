package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentMethod {

    private int paymentMethodId;
    private String paymentMethod;
    private BankDetails bankDetails;
    private String description;


    public PaymentMethod(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
