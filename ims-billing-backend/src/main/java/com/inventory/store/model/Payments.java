package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Payments {

    private int paymentId;
    private String amount;
    private String paymentDate;
    private String invoiceId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String paymentReceivedBy;
    private String transactionId;


    public Payments(int paymentId) {
        this.paymentId=paymentId;
    }
}
