package com.assessment.domain.repo;

import com.assessment.domain.model.Product;

import java.util.List;
/**
 * Logic interface for retrieving products.
 */
public interface IProduct {
    /**
     * Retrieves all payments made by users.
     *
     * @return a list of all {@link Product} records
     */
    List<Product> findAll();
}
