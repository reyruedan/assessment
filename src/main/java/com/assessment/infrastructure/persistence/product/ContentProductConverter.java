package com.assessment.infrastructure.persistence.product;

import com.assessment.domain.model.Payment;
import com.assessment.domain.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA attribute converter that converts an {@link Product} object to its JSON string representation for database storage,
 * and converts the JSON string back to an {@link Product} object when reading from the database.
 * <p>
 * The {@code @Converter(autoApply = true)} annotation indicates that this converter
 * will be automatically applied to all attributes of type {@code Product} in all entity classes,
 * without needing to specify {@code @Convert} explicitly.
 */
@Converter(autoApply = true)
public class ContentProductConverter implements AttributeConverter<Product, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Product attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting ContentData to JSON", e);
        }
    }

    @Override
    public Product convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Product.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to ContentData", e);
        }
    }
}

