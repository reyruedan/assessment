package com.assessment.application.service;

import com.assessment.domain.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAmountPaidPerUser();
    List<Payment> getAmountOwedPerUser();
    void loadUserPayment(Payment payment);
}

