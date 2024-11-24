package com.inventory.store.service;

import com.inventory.store.model.*;
import com.inventory.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private PaymentsService paymentsService;

    public void addOrder(Order order) {
        int paymentId = paymentsService.addPayments(order.getPayment());
        order.getPayment().setPaymentId(paymentId);
        int orderId = orderRepository.addOrder(order);
        productService.addOrderProduct(orderId, order.getProduct());
    }

    public Order getOrderById(int orderId) {
        Order order = orderRepository.getOrderById(orderId);
        Customer customer = customerService.getCustomerById(order.getCustomer().getCustomerId()); // Implement this method accordingly
        List<Product> products = productService.getProductByOrderId(orderId);
        Discount discounts = discountService.getDiscountByDiscountId(order.getDiscount().getDiscountId());
        Payments payments = paymentsService.getPaymentByPaymentId(order.getPayment().getPaymentId());
        order.setCustomer(customer);
        order.setProduct(products);
        order.setDiscount(discounts);
        order.setPayment(payments);
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.getAllOrders();
        for (Order order : orders) {
            Customer customer = customerService.getCustomerById(order.getCustomer().getCustomerId()); // Implement this method accordingly
            List<Product> products = productService.getProductByOrderId(order.getOrderId());
            Discount discounts = discountService.getDiscountByDiscountId(order.getDiscount().getDiscountId());
            Payments payments = paymentsService.getPaymentByPaymentId(order.getPayment().getPaymentId());
            order.setCustomer(customer);
            order.setProduct(products);
            order.setDiscount(discounts);
            order.setPayment(payments);
        }
        return orders;

    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderRepository.getOrdersByCustomerId(customerId);
    }
}
