package com.assessment.infrastructure.persistence.order;

import com.assessment.domain.model.Order;
import com.assessment.domain.repo.IOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements IOrder {

    private final JsonContentOrderRepository jpaRepository;

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity -> new Order(entity.getContent().user(), entity.getContent().drink(), entity.getContent().size()))
                .toList();
    }
}

