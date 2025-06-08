package com.assessment.application.service;

import com.assessment.application.service.calculator.PaymentCalculator;
import com.assessment.application.service.calculator.ProductPriceCalculator;
import com.assessment.domain.model.Order;
import com.assessment.domain.model.Payment;
import com.assessment.domain.repo.IOrder;
import com.assessment.domain.repo.IPayment;
import com.assessment.domain.repo.IProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link PaymentService} interface.
 * Handles business logic related to user payments and debts.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);


    private final IPayment userPayment;
    private final IOrder userOrder;
    private final IProduct userProduct;

    public PaymentServiceImpl(IPayment userPayment, IOrder userOrder, IProduct userProduct) {
        this.userPayment = userPayment;
        this.userOrder = userOrder;
        this.userProduct = userProduct;
    }

    /**
     * Retrieves the total amount paid by each user.
     *
     * @return a list of {@link Payment} objects, each representing a user and their total paid amount
     */
    @Override
    public List<Payment> getAmountPaidPerUser() {
        log.info("get all payment list by user");
        List<Payment> allPayments = userPayment.findAll();

        log.info("total size list: {}", allPayments.size());

        return allPayments.stream()
                .collect(Collectors.groupingBy(
                        Payment::user,
                        Collectors.reducing(BigDecimal.ZERO, Payment::amount, BigDecimal::add)
                ))
                .entrySet().stream()
                .map(entry -> new Payment(entry.getKey(), entry.getValue()))
                .toList();
    }

    /**
     * Calculates the amount owed by each user based on their orders and payments.
     *
     * @return a list of {@link Payment} objects representing user debts
     */
    @Override
    public List<Payment> getAmountOwedPerUser() {
        List<Order> orders = userOrder.findAll();
        List<Payment> payments = userPayment.findAll();
        PaymentCalculator paymentCalculator = new PaymentCalculator(
                new ProductPriceCalculator(userProduct.findAll())
        );
        log.info("total orders size list: {}, total payments size list: {}", orders.size(), payments.size());
        Map<String, BigDecimal> totalOrderedByUser = paymentCalculator.calculateTotalOrderedByUser(orders);
        Map<String, BigDecimal> totalPaidByUser = paymentCalculator.calculateTotalPaidByUser(payments);
        return paymentCalculator.calculateAmountOwedPerUser(totalOrderedByUser, totalPaidByUser);

    }

    /**
     * Saves a new user payment to the system.
     *
     * @param payment the payment to be stored
     */
    @Override
    public void loadUserPayment(Payment payment) {
        userPayment.payment(payment);
    }
}


