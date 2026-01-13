package com.inditex.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Price(
        long productId,
        long brandId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int priority,
        BigDecimal amount,
        String currency
) {
    public Price {
        Objects.requireNonNull(startDate, "startDate is required");
        Objects.requireNonNull(endDate, "endDate is required");
        Objects.requireNonNull(amount, "amount is required");
        Objects.requireNonNull(currency, "currency is required");

        if (productId <= 0) throw new IllegalArgumentException("productId must be positive");
        if (brandId <= 0) throw new IllegalArgumentException("brandId must be positive");
        if (priceList <= 0) throw new IllegalArgumentException("priceList must be positive");
        if (priority < 0) throw new IllegalArgumentException("priority must be >= 0");
        if (currency.isBlank()) throw new IllegalArgumentException("currency must not be blank");
        if (endDate.isBefore(startDate)) throw new IllegalArgumentException("endDate must be after startDate");
    }
}
