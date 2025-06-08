package com.assessment.interfaces.controller;

import com.assessment.application.service.PaymentService;
import com.assessment.dto.UserOwedDTO;
import com.assessment.dto.UserPaymentDTO;
import com.assessment.infrastructure.mapper.UserOwedMapper;
import com.assessment.infrastructure.mapper.UserPaymentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserPaymentController {

    private final PaymentService paymentService;
    private final UserPaymentMapper userPaymentMapper;
    private final UserOwedMapper userOwedMapper;

    public UserPaymentController(PaymentService paymentService, UserPaymentMapper userPaymentMapper, UserOwedMapper userOwedMapper) {
        this.paymentService = paymentService;
        this.userPaymentMapper = userPaymentMapper;
        this.userOwedMapper = userOwedMapper;
    }

    @GetMapping("/paid")
    public ResponseEntity<List<UserPaymentDTO>> getAmountPaidByUsers() {
        return ResponseEntity.ok(userPaymentMapper.toDTOList(paymentService.getAmountPaidPerUser()));
    }

    @GetMapping("/owed")
    public ResponseEntity<List<UserOwedDTO>> getUserOwedAmounts() {
        return ResponseEntity.ok(userOwedMapper.toDTOList(paymentService.getAmountOwedPerUser()));
    }

    @PostMapping
    public ResponseEntity<Void> receivePayment(@RequestBody UserPaymentDTO payment) {
        paymentService.loadUserPayment(userPaymentMapper.toPayment(payment));
        return ResponseEntity.ok().build();
    }
}