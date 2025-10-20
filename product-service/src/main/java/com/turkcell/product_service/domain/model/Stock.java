package com.turkcell.product_service.domain.model;

import java.util.Objects;

/**
 * Value Object representing product stock quantity
 */
public final class Stock {
    private final int value;

    private Stock(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.value = value;
    }

    public static Stock of(int value) {
        return new Stock(value);
    }

    public static Stock zero() {
        return new Stock(0);
    }

    public int getValue() {
        return value;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isAvailable() {
        return value > 0;
    }

    public Stock add(Stock other) {
        return new Stock(this.value + other.value);
    }

    public Stock subtract(Stock other) {
        int newValue = this.value - other.value;
        if (newValue < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        return new Stock(newValue);
    }

    public Stock add(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot add negative quantity");
        }
        return new Stock(this.value + quantity);
    }

    public Stock subtract(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot subtract negative quantity");
        }
        int newValue = this.value - quantity;
        if (newValue < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        return new Stock(newValue);
    }

    public boolean hasEnough(Stock required) {
        return this.value >= required.value;
    }

    public boolean hasEnough(int required) {
        return this.value >= required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return value == stock.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
