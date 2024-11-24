package com.inventory.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankDetails {

    private int bankDetailsId;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branch;
    private String ownerName;
    private String description;


    public BankDetails(int bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }
}
