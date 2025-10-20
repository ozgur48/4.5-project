package com.turkcell.product_service.domain.model;

import java.util.Objects;

/**
 * Value Object representing a Product description
 */
public final class ProductDescription {
    private final String value;

    private ProductDescription(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Product description cannot be null");
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Product description cannot exceed 500 characters");
        }
        this.value = value.trim();
    }

    public static ProductDescription of(String value) {
        return new ProductDescription(value);
    }

    public static ProductDescription empty() {
        return new ProductDescription("");
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDescription that = (ProductDescription) o;
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
