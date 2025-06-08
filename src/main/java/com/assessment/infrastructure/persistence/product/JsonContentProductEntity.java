package com.assessment.infrastructure.persistence.product;

import com.assessment.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonContentProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ContentProductConverter.class)
    private Product content;

    public JsonContentProductEntity(Product content) {
        this.content = content;
    }
}
