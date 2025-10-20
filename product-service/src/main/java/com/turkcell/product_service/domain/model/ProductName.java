package com.turkcell.product_service.domain.model;

import java.util.Objects;

/**
 * Value Object representing a Product name
 */
public final class ProductName {
    private final String value;

    private ProductName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("Product name cannot exceed 100 characters");
        }
        this.value = value.trim();
    }

    public static ProductName of(String value) {
        return new ProductName(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
