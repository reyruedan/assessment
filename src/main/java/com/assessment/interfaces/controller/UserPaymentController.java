package com.assessment.interfaces.controller;

import com.assessment.application.service.PaymentService;
import com.assessment.dto.UserOwedDTO;
import com.assessment.dto.UserPaymentDTO;
import com.assessment.infrastructure.mapper.UserOwedMapper;
import com.assessment.infrastructure.mapper.UserPaymentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling user payments and owed amounts.
 * Provides endpoints to retrieve amounts paid, amounts owed,
 * and to receive new payments.
 */
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

    /**
     * Retrieves the amount paid by each user.
     *
     * @return a list of UserPaymentDTO objects representing the amounts paid
     */
    @GetMapping("/paid")
    public ResponseEntity<List<UserPaymentDTO>> getAmountPaidByUsers() {
        return ResponseEntity.ok(userPaymentMapper.toDTOList(paymentService.getAmountPaidPerUser()));
    }

    /**
     * Retrieves the amount owed by each user.
     *
     * @return a list of UserOwedDTO objects representing the debts
     */
    @GetMapping("/owed")
    public ResponseEntity<List<UserOwedDTO>> getUserOwedAmounts() {
        return ResponseEntity.ok(userOwedMapper.toDTOList(paymentService.getAmountOwedPerUser()));
    }

    /**
     * Receives a payment from a user and stores it.
     *
     * @param payment the DTO representing the payment made by a user
     * @return HTTP 200 OK if the payment is processed successfully
     */
    @PostMapping
    public ResponseEntity<Void> receivePayment(@RequestBody UserPaymentDTO payment) {
        paymentService.loadUserPayment(userPaymentMapper.toPayment(payment));
        return ResponseEntity.ok().build();
    }
}