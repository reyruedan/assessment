package com.assessment.domain.model;
import java.math.BigDecimal;

public record Payment(
        String user,
        BigDecimal amount
) {}



