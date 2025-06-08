package com.assessment.assessment.domain.repo;

import com.assessment.assessment.domain.model.Payment;

import java.util.List;

public interface IPayment {
    void payment(Payment payment);
    List<Payment> findAll();
}
