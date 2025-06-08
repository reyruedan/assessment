package com.assessment.assessment.infrastructure.persistence.product;

import com.assessment.assessment.domain.model.Product;
import com.assessment.assessment.domain.repo.IProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProduct {

    private final JsonContentProductRepository jpaRepository;

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity -> new Product(entity.getContent().drink_name(), entity.getContent().prices()))
                .toList();
    }
}

