package com.inventory.store.service;

import com.inventory.store.model.PaymentMethod;
import com.inventory.store.model.PaymentStatus;
import com.inventory.store.model.Payments;
import com.inventory.store.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentsService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private PaymentStatusService paymentStatusService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    public void addOrderPayments(int orderId, List<Payments> payments) {
        List<Integer> paymentIds = addAllPayments(payments);
        paymentsRepository.addOrderPayments(orderId, paymentIds);
    }

    public List<Integer> addAllPayments(List<Payments> payments) {
        for (Payments payment : payments) {
            int paymentMethodId = paymentMethodService.addPaymentMethod(payment.getPaymentMethod());
            int paymentStatusId = paymentStatusService.addPaymentStatus(payment.getPaymentStatus());
            payment.setPaymentMethod(new PaymentMethod(paymentMethodId));
            payment.setPaymentStatus(new PaymentStatus(paymentStatusId));
        }
        return paymentsRepository.addPayments(payments);
    }

    public int addPayments(Payments payment) {
        int paymentMethodId = paymentMethodService.addPaymentMethod(payment.getPaymentMethod());
        int paymentStatusId = paymentStatusService.addPaymentStatus(payment.getPaymentStatus());
        payment.setPaymentMethod(new PaymentMethod(paymentMethodId));
        payment.setPaymentStatus(new PaymentStatus(paymentStatusId));
        return paymentsRepository.addPayments(payment);
    }

    public List<Payments> getPaymentsByOrderId(int orderId) {
        List<Payments> payments = paymentsRepository.getPaymentsByOrderId(orderId);
        for (Payments payment : payments) {
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(payment.getPaymentMethod().getPaymentMethodId());
            PaymentStatus paymentStatus = paymentStatusService.getPaymentStatusById(payment.getPaymentStatus().getPaymentStatusId());
            payment.setPaymentMethod(paymentMethod);
            payment.setPaymentStatus(paymentStatus);
        }
        return payments;
    }

    public List<Payments> getAllPayments() {
        List<Payments> paymentsList =  paymentsRepository.getAllPayments();
        for(Payments payment:paymentsList){
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(payment.getPaymentMethod().getPaymentMethodId());
            PaymentStatus paymentStatus = paymentStatusService.getPaymentStatusById(payment.getPaymentStatus().getPaymentStatusId());
            payment.setPaymentMethod(paymentMethod);
            payment.setPaymentStatus(paymentStatus);
        }

        return paymentsList;
    }


    public Payments getPaymentByPaymentId(int paymentId) {
        Payments payment = paymentsRepository.getPaymentsByPaymentId(paymentId);
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(payment.getPaymentMethod().getPaymentMethodId());
        PaymentStatus paymentStatus = paymentStatusService.getPaymentStatusById(payment.getPaymentStatus().getPaymentStatusId());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(paymentStatus);
        return payment;
    }
}
