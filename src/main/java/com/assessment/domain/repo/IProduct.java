package com.assessment.domain.repo;

import com.assessment.domain.model.Product;

import java.util.List;

public interface IProduct {
    List<Product> findAll();
}
