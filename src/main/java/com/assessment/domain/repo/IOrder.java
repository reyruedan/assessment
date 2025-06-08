package com.assessment.domain.repo;

import com.assessment.domain.model.Order;
import com.assessment.domain.model.Payment;

import java.util.List;
/**
 * Logic interface for retrieving user orders.
 */
public interface IOrder {
    /**
     * Retrieves all payments made by users.
     *
     * @return a list of all {@link Order} records
     */
    List<Order> findAll();
}
