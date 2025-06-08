package com.assessment.assessment.domain.model;

import java.math.BigDecimal;
import java.util.Map;
public record Product(
        String drink_name,
        Map<String, BigDecimal> prices
) {}
