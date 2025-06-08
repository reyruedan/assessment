package com.assessment.infrastructure.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonContentOrderRepository extends JpaRepository<JsonContentOrderEntity, Long> {
}

