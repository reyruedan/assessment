package com.assessment.domain.repo;

import com.assessment.domain.model.Order;

import java.util.List;

public interface IOrder {
    List<Order> findAll();
}
