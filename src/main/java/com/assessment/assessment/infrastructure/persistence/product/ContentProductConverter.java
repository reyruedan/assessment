package com.assessment.assessment.infrastructure.persistence.product;

import com.assessment.assessment.domain.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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

