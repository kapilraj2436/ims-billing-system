package com.inventory.store.service;

import com.inventory.store.model.BankDetails;
import com.inventory.store.repository.BankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankDetailsService {

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    public int addBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.addBankDetails(bankDetails);
    }

    public void updateBankDetails(BankDetails bankDetails) {
        bankDetailsRepository.updateBankDetails(bankDetails);
    }

    public BankDetails getBankDetailsById(int bankDetailsId) {
        return bankDetailsRepository.getBankDetailsById(bankDetailsId);
    }

    public List<BankDetails> getAllBankDetails() {
        return bankDetailsRepository.getAllBankDetails();
    }

}
