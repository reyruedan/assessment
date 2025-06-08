package com.assessment.infrastructure.persistence.payment;

import com.assessment.domain.model.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonContentPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ContentPaymentConverter.class)
    private Payment content;

    public JsonContentPaymentEntity(Payment content) {
        this.content = content;
    }
}
