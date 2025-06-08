package com.assessment.infrastructure.mapper;

import com.assessment.domain.model.Payment;
import com.assessment.dto.UserPaymentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper class responsible for converting between {@link Payment} entities and {@link UserPaymentDTO} data transfer objects.
 */
@Component
public class UserPaymentMapper {
    /**
     * Converts a single {@link Payment} object to a {@link UserPaymentDTO}.
     *
     * @param userPayment the {@link Payment} instance to convert
     * @return the corresponding {@link UserPaymentDTO}
     */
    public UserPaymentDTO toDTO(Payment userPayment) {
        return new UserPaymentDTO(userPayment.user(), userPayment.amount());
    }

    /**
     * Converts a list of {@link Payment} objects to a list of {@link UserPaymentDTO} objects.
     *
     * @param list the list of {@link Payment} instances to convert
     * @return a list of corresponding {@link UserPaymentDTO} objects
     */
    public List<UserPaymentDTO> toDTOList(List<Payment> list) {
        return list.stream().map(this::toDTO).toList();
    }

    /**
     * Converts a {@link UserPaymentDTO} object to a {@link Payment} entity.
     *
     * @param userPayment the {@link UserPaymentDTO} to convert
     * @return the corresponding {@link Payment} entity
     */
    public Payment toPayment(UserPaymentDTO userPayment) {
        return new Payment(userPayment.userId(), userPayment.amountPaid());
    }
}