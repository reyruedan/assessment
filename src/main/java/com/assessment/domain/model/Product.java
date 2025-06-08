package com.assessment.domain.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents a drink product with different size-based prices.
 *
 * @param drink_name the name of the drink (e.g., "coffee", "tea")
 * @param prices     a map containing size-price pairs (e.g., "small" → 2.50, "large" → 3.50)
 */
public record Product(
        String drink_name,
        Map<String, BigDecimal> prices
) {}
