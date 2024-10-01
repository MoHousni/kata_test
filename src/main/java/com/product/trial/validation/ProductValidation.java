package com.product.trial.validation;

import com.product.trial.entity.Product;

import java.math.BigDecimal;

public class ProductValidation {
    static public void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
    }
}
