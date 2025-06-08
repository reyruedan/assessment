package com.assessment.infrastructure.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonContentProductRepository extends JpaRepository<JsonContentProductEntity, Long> {
}

