package com.inventory.store.service;

import com.inventory.store.model.Discount;
import com.inventory.store.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public void addOrderDiscount(int orderId, List<Discount> discount) {
        List<Integer> discountIds = discount.stream().map(Discount::getDiscountId).toList();
        discountRepository.addOrderDiscount(orderId, discountIds);
    }

    public List<Discount> getDiscountByOrderId(int orderId) {

        return discountRepository.getDiscountByOrderId(orderId);
    }

    public void addDiscount(Discount discount) {
        discountRepository.addDiscount(discount);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.getAllDiscounts();
    }

    public Discount getDiscountByDiscountId(int discountId) {
        return discountRepository.getDiscountByDiscountId(discountId);
    }
}
