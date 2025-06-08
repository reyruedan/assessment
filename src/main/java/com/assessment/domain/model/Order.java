package com.assessment.domain.model;

/**
 * Represents an order made by a user.
 *
 * @param user  the identifier (e.g., username or user ID) of the person who placed the order
 * @param drink the name of the drink ordered
 * @param size  the size of the drink (e.g., small, medium, large)
 */
public record Order(
        String user,
        String drink,
        String size
) {}
