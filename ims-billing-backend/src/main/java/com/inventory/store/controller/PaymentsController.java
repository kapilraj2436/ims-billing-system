package com.inventory.store.controller;

import com.inventory.store.common.CommonController;
import com.inventory.store.model.Payments;
import com.inventory.store.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController implements CommonController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping("/add")
    public int addPayment(@RequestBody Payments payments) {
        return paymentsService.addPayments(payments);
    }

    @PostMapping("/addAll")
    public void addAllPayments(@RequestBody List<Payments> payments) {
        paymentsService.addAllPayments(payments);
    }


    @GetMapping("/search")
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    @GetMapping("/search/{paymentId}")
    public Payments getPaymentByPaymentId(@PathVariable int paymentId) {
        return paymentsService.getPaymentByPaymentId(paymentId);
    }
}
