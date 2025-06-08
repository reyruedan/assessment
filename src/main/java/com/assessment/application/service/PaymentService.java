package com.assessment.application.service;

import com.assessment.domain.model.Payment;

import java.util.List;

public interface PaymentService {
    /**
     * Retrieves the total amount paid by each user.
     *
     * @return a list of {@link Payment} objects, each containing a user identifier and the total amount paid
     */
    List<Payment> getAmountPaidPerUser();

    /**
     * Calculates the amount owed by each user based on their orders and payments.
     *
     * @return a list of {@link Payment} objects, each containing a user identifier and the remaining debt
     */
    List<Payment> getAmountOwedPerUser();

    /**
     * Loads or registers a new payment made by a user.
     *
     * @param payment the {@link Payment} object to be stored
     */
    void loadUserPayment(Payment payment);
}

