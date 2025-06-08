package com.assessment.application.service.calculator;

import com.assessment.domain.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductPriceCalculator {

    private static final Logger log = LoggerFactory.getLogger(ProductPriceCalculator.class);

    private final Map<String, Product> productsByDrink;

    public ProductPriceCalculator(List<Product> products) {
        this.productsByDrink = products.stream()
                .collect(Collectors.toMap(Product::drink_name, Function.identity()));
    }

    public BigDecimal getPrice(String drink, String size) {
        log.debug("getPrice drink: {}, size: {}", drink, size);
        Product product = productsByDrink.get(drink);
        if (product == null) {
            log.error("product not found drink: {}, size: {}", drink, size);
            return BigDecimal.ZERO;
        }
        BigDecimal price = product.prices().get(size);
        if (price == null) {
            log.error("price not found: {}, drink: {}", drink, size);
            return BigDecimal.ZERO;
        }
        return price;
    }
}
