package com.assessment.application.service;


import com.assessment.domain.model.Order;
import com.assessment.domain.model.Payment;
import com.assessment.domain.model.Product;
import com.assessment.domain.repo.IOrder;
import com.assessment.domain.repo.IPayment;
import com.assessment.domain.repo.IProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {

    @Mock
    IPayment userPayment;

    @Mock
    IOrder userOrder;

    @Mock
    IProduct userProduct;

    @InjectMocks
    PaymentServiceImpl paymentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAmountPaidPerUser_shouldReturnGroupedPayments() {
        List<Payment> payments = List.of(
                new Payment("user1", new BigDecimal("10")),
                new Payment("user1", new BigDecimal("5")),
                new Payment("user2", new BigDecimal("7"))
        );
        when(userPayment.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAmountPaidPerUser();

        assertThat(result).hasSize(2);
        assertThat(result).anyMatch(p -> p.user().equals("user1") && p.amount().equals(new BigDecimal("15")));
        assertThat(result).anyMatch(p -> p.user().equals("user2") && p.amount().equals(new BigDecimal("7")));

        verify(userPayment).findAll();
    }

    @Test
    void getAmountOwedPerUser_shouldCalculateAmounts() {
        List<Order> orders = List.of(
                new Order("user1", "latte", "small"),
                new Order("user2", "mocha", "medium")
        );
        List<Payment> payments = List.of(
                new Payment("user1", new BigDecimal("3")),
                new Payment("user2", new BigDecimal("4"))
        );
        List<Product> products = List.of(
                new Product("latte", Map.of("small", new BigDecimal("5.0"))),
                new Product("mocha", Map.of("medium", new BigDecimal("6.0")))
        );

        when(userOrder.findAll()).thenReturn(orders);
        when(userPayment.findAll()).thenReturn(payments);
        when(userProduct.findAll()).thenReturn(products);

        List<Payment> result = paymentService.getAmountOwedPerUser();

        assertThat(result).hasSize(2);
        assertThat(result).anyMatch(p -> p.user().equals("user1") && p.amount().compareTo(new BigDecimal("2.0")) == 0);
        assertThat(result).anyMatch(p -> p.user().equals("user2") && p.amount().compareTo(new BigDecimal("2.0")) == 0);

        verify(userOrder).findAll();
        verify(userPayment).findAll();
        verify(userProduct).findAll();
    }

    @Test
    void loadUserPayment_shouldCallPayment() {
        Payment payment = new Payment("user1", BigDecimal.TEN);

        paymentService.loadUserPayment(payment);

        verify(userPayment).payment(payment);
    }
}