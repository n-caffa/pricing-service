package com.inditex.pricing.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record PriceResponse(
        long productId,
        long brandId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency
) {
    public PriceResponse {
        Objects.requireNonNull(startDate, "startDate is required");
        Objects.requireNonNull(endDate, "endDate is required");
        Objects.requireNonNull(price, "price is required");
        Objects.requireNonNull(currency, "currency is required");
    }
}
