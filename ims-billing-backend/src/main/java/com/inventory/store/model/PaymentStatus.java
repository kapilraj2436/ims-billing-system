package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentStatus {

    private int paymentStatusId;
    private String paymentStatusName;
    private String description;

    public PaymentStatus(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }
}
