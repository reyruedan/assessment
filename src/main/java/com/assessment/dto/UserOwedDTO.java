package com.assessment.dto;

import java.math.BigDecimal;

public record UserOwedDTO(String userId, BigDecimal amountOwed) { }
