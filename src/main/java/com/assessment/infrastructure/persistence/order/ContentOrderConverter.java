package com.assessment.infrastructure.persistence.order;

import com.assessment.domain.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContentOrderConverter implements AttributeConverter<Order, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Order attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Order ContentData to JSON", e);
        }
    }

    @Override
    public Order convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Order.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Order JSON to ContentData", e);
        }
    }
}

