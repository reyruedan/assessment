package com.assessment.application.service.calculator;

import com.assessment.domain.model.Order;
import com.assessment.domain.model.Payment;

import java.math.BigDecimal;
import java.util.*;

public class PaymentCalculator {
    private final ProductPriceCalculator priceCalculator;

    public PaymentCalculator(ProductPriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public Map<String, BigDecimal> calculateTotalOrderedByUser(List<Order> orders) {
        Map<String, BigDecimal> totalOrderedByUser = new HashMap<>();
        for (Order order : orders) {
            BigDecimal price = priceCalculator.getPrice(order.drink(), order.size());
            totalOrderedByUser.merge(order.user(), price, BigDecimal::add);
        }
        return totalOrderedByUser;
    }

    public Map<String, BigDecimal> calculateTotalPaidByUser(List<Payment> payments) {
        Map<String, BigDecimal> totalPaidByUser = new HashMap<>();
        for (Payment payment : payments) {
            totalPaidByUser.merge(payment.user(), payment.amount(), BigDecimal::add);
        }
        return totalPaidByUser;
    }

    public List<Payment> calculateAmountOwedPerUser(Map<String, BigDecimal> totalOrderedByUser,
                                                    Map<String, BigDecimal> totalPaidByUser) {
        Set<String> users = new HashSet<>();
        users.addAll(totalOrderedByUser.keySet());
        users.addAll(totalPaidByUser.keySet());

        return users.stream()
                .map(user -> {
                    BigDecimal ordered = totalOrderedByUser.getOrDefault(user, BigDecimal.ZERO);
                    BigDecimal paid = totalPaidByUser.getOrDefault(user, BigDecimal.ZERO);
                    BigDecimal owed = ordered.subtract(paid);
                    return new Payment(user, owed);
                })
                .filter(payment -> payment.amount().compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }
}
