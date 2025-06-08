package com.assessment.assessment.infrastructure.mapper;

import com.assessment.assessment.domain.model.Payment;
import com.assessment.assessment.dto.UserPaymentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPaymentMapper {
    public UserPaymentDTO toDTO(Payment userPayment) {
        return new UserPaymentDTO(userPayment.user(), userPayment.amount());
    }

    public List<UserPaymentDTO> toDTOList(List<Payment> list) {
        return list.stream().map(this::toDTO).toList();
    }

    public Payment toPayment(UserPaymentDTO userPayment) {
        return new Payment(userPayment.userId(), userPayment.amountPaid());
    }
}