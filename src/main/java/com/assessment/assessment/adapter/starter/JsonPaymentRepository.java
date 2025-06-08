package com.assessment.assessment.adapter.starter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonPaymentRepository extends JpaRepository<JsonPaymentsFile, Long> {
}
