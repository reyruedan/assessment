package com.assessment.assessment.infrastructure.persistence.payment;

import com.assessment.assessment.domain.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContentPaymentConverter implements AttributeConverter<Payment, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Payment attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting ContentData to JSON", e);
        }
    }

    @Override
    public Payment convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Payment.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to ContentData", e);
        }
    }
}

