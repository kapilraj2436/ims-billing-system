package com.inventory.store.service;

import com.inventory.store.model.PaymentStatus;
import com.inventory.store.repository.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentStatusService {

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    public int addPaymentStatus(PaymentStatus paymentStatus) {
        return paymentStatusRepository.addPaymentStatus(paymentStatus);
    }

    public PaymentStatus getPaymentStatusById(int paymentStatusId) {
        return paymentStatusRepository.getPaymentStatusById(paymentStatusId);
    }
}
