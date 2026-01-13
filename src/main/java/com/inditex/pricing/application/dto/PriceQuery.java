package com.inditex.pricing.application.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public record PriceQuery(long brandId, long productId, LocalDateTime applicationDate) {

    public PriceQuery {
        Objects.requireNonNull(applicationDate, "applicationDate is required");
        if (brandId <= 0) throw new IllegalArgumentException("brandId must be positive");
        if (productId <= 0) throw new IllegalArgumentException("productId must be positive");
    }
}
