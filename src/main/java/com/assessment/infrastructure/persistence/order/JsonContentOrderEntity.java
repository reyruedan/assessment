package com.assessment.infrastructure.persistence.order;

import com.assessment.domain.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonContentOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ContentOrderConverter.class)
    private Order content;

    public JsonContentOrderEntity(Order content) {
        this.content = content;
    }
}
