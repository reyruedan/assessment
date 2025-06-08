package com.assessment.assessment.domain.repo;

import com.assessment.assessment.domain.model.Product;

import java.util.List;

public interface IProduct {
    List<Product> findAll();
}
