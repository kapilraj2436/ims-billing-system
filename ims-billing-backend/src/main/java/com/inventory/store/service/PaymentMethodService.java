package com.inventory.store.service;

import com.inventory.store.model.PaymentMethod;
import com.inventory.store.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private BankDetailsService bankDetailsService;

    public int addPaymentMethod(PaymentMethod paymentMethod) {
        int bankDetailId = bankDetailsService.addBankDetails(paymentMethod.getBankDetails());
        paymentMethod.getBankDetails().setBankDetailsId(bankDetailId);
        return paymentMethodRepository.addPaymentMethod(paymentMethod);
    }

    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRepository.getPaymentMethodById(paymentMethodId);
        paymentMethod.setBankDetails(
                bankDetailsService.getBankDetailsById(paymentMethod.getBankDetails().getBankDetailsId()));
        return paymentMethod;
    }
}
