package com.assessment.domain.repo;

import com.assessment.domain.model.Payment;

import java.util.List;

public interface IPayment {
    void payment(Payment payment);
    List<Payment> findAll();
}
