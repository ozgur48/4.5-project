package com.turkcell.product_service.domain.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Value Object representing a currency code
 */
public final class Currency {
    private final String code;

    private Currency(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency code cannot be null or empty");
        }
        String normalizedCode = code.trim().toUpperCase();
        if (normalizedCode.length() != 3) {
            throw new IllegalArgumentException("Currency code must be exactly 3 characters");
        }
        if (!isValidCurrencyCode(normalizedCode)) {
            throw new IllegalArgumentException("Invalid currency code: " + normalizedCode);
        }
        this.code = normalizedCode;
    }

    public static Currency of(String code) {
        return new Currency(code);
    }

    public static Currency TRY() {
        return new Currency("TRY");
    }

    public static Currency USD() {
        return new Currency("USD");
    }

    public static Currency EUR() {
        return new Currency("EUR");
    }

    public String getCode() {
        return code;
    }

    private boolean isValidCurrencyCode(String code) {
        // Common currency codes - can be extended
        String[] validCodes = {
            "TRY", "USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "SEK", "NOK", "DKK"
        };
        return Arrays.asList(validCodes).contains(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}
