package com.assessment.assessment.interfaces.controller;


import com.assessment.assessment.application.service.PaymentService;
import com.assessment.assessment.domain.model.Payment;
import com.assessment.assessment.dto.UserOwedDTO;
import com.assessment.assessment.dto.UserPaymentDTO;
import com.assessment.assessment.infrastructure.mapper.UserOwedMapper;
import com.assessment.assessment.infrastructure.mapper.UserPaymentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserPaymentControllerTest {
    @Mock
    PaymentService paymentService;

    @Mock
    UserPaymentMapper userPaymentMapper;

    @Mock
    UserOwedMapper userOwedMapper;

    @InjectMocks
    UserPaymentController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPaidAmounts() {
        var payments = List.of(mock(Payment.class));
        var dtos = List.of(mock(UserPaymentDTO.class));

        when(paymentService.getAmountPaidPerUser()).thenReturn(payments);
        when(userPaymentMapper.toDTOList(payments)).thenReturn(dtos);

        ResponseEntity<List<UserPaymentDTO>> response = controller.getAmountPaidByUsers();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(dtos);

        verify(paymentService).getAmountPaidPerUser();
        verify(userPaymentMapper).toDTOList(payments);
    }

    @Test
    void shouldReturnOwedAmounts() {
        var owed = List.of(mock(Payment.class));
        var owedDtos = List.of(mock(UserOwedDTO.class));

        when(paymentService.getAmountOwedPerUser()).thenReturn(owed);
        when(userOwedMapper.toDTOList(owed)).thenReturn(owedDtos);

        ResponseEntity<List<UserOwedDTO>> response = controller.getUserOwedAmounts();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(owedDtos);

        verify(paymentService).getAmountOwedPerUser();
        verify(userOwedMapper).toDTOList(owed);
    }

    @Test
    void shouldProcessPayment() {
        UserPaymentDTO dto = new UserPaymentDTO("", BigDecimal.ONE);
        Payment payment = mock(Payment.class);

        when(userPaymentMapper.toPayment(dto)).thenReturn(payment);

        ResponseEntity<Void> response = controller.receivePayment(dto);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(userPaymentMapper).toPayment(dto);
        verify(paymentService).loadUserPayment(payment);
    }
}