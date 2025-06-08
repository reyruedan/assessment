package com.assessment.infrastructure.mapper;

import com.assessment.domain.model.Payment;
import com.assessment.dto.UserOwedDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper class responsible for converting {@link Payment} entities to {@link UserOwedDTO} objects.
 */
@Component
public class UserOwedMapper {
    /**
     * Converts a single {@link Payment} object to a {@link UserOwedDTO}.
     *
     * @param userPayment the {@link Payment} instance to convert
     * @return the corresponding {@link UserOwedDTO}
     */
    public UserOwedDTO toDTO(Payment userPayment) {
        return new UserOwedDTO(userPayment.user(), userPayment.amount());
    }

    /**
     * Converts a list of {@link Payment} objects to a list of {@link UserOwedDTO} objects.
     *
     * @param list the list of {@link Payment} instances to convert
     * @return a list of corresponding {@link UserOwedDTO} objects
     */
    public List<UserOwedDTO> toDTOList(List<Payment> list) {
        return list.stream().map(this::toDTO).toList();

    }
}