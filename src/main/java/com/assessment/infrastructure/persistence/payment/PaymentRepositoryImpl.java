package com.assessment.infrastructure.persistence.payment;

import com.assessment.domain.model.Payment;
import com.assessment.domain.repo.IPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements IPayment {

    private final JsonContentPaymentRepository jpaRepository;

    @Override
    public void payment(Payment payment) {
        JsonContentPaymentEntity entity = new JsonContentPaymentEntity();
        entity.setContent(payment);
        jpaRepository.save(entity);
    }

    @Override
    public List<Payment> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity -> new Payment(entity.getContent().user(), entity.getContent().amount()))
                .toList();
    }
}

