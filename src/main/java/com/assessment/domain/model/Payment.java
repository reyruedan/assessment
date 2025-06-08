package com.assessment.domain.model;
import java.math.BigDecimal;

/**
 * Represents a payment made by a user.
 *
 * @param user   the identifier (e.g., username or user ID) of the person who made the payment
 * @param amount the amount of money paid by the user
 */
public record Payment(
        String user,
        BigDecimal amount
) {}



