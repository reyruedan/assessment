package com.assessment.assessment.infrastructure.mapper;

import com.assessment.assessment.domain.model.Payment;
import com.assessment.assessment.dto.UserOwedDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserOwedMapper {
    public UserOwedDTO toDTO(Payment userPayment) {
        return new UserOwedDTO(userPayment.user(), userPayment.amount());
    }

    public List<UserOwedDTO> toDTOList(List<Payment> list) {
        return list.stream().map(this::toDTO).toList();

    }
}