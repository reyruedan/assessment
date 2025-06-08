package com.assessment.domain.repo;

import com.assessment.domain.model.Payment;

import java.util.List;
/**
 * Repository interface for managing user payments.
 */
public interface IPayment {

    /**
     * Saves or records a new payment.
     *
     * @param payment the {@link Payment} object to be saved
     */
    void payment(Payment payment);

    /**
     * Retrieves all payments made by users.
     *
     * @return a list of all {@link Payment} records
     */
    List<Payment> findAll();
}
