package com.assessment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonSerialize
public record UserPaymentDTO(String userId, BigDecimal amountPaid) {}
