package com.assessment.adapter.starter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonOrderRepository extends JpaRepository<JsonOrdersFile, Long> {
}
