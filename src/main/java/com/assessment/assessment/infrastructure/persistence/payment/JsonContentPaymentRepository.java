package com.assessment.assessment.infrastructure.persistence.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonContentPaymentRepository extends JpaRepository<JsonContentPaymentEntity, Long> {
}

