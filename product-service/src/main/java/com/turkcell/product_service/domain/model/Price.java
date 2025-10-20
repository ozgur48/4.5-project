package com.turkcell.product_service.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value Object representing a Product price
 */
public final class Price {
    private final BigDecimal value;

    private Price(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (value.scale() > 2) {
            throw new IllegalArgumentException("Price cannot have more than 2 decimal places");
        }
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public static Price of(double value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price of(String value) {
        return new Price(new BigDecimal(value));
    }

    public static Price zero() {
        return new Price(BigDecimal.ZERO);
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Price add(Price other) {
        return new Price(this.value.add(other.value));
    }

    public Price subtract(Price other) {
        return new Price(this.value.subtract(other.value));
    }

    public Price multiply(BigDecimal factor) {
        return new Price(this.value.multiply(factor));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
