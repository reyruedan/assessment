package com.assessment.assessment.domain.repo;

import com.assessment.assessment.domain.model.Order;

import java.util.List;

public interface IOrder {
    List<Order> findAll();
}
