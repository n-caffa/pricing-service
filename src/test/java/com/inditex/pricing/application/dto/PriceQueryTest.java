package com.inditex.pricing.application.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PriceQueryTest {

    @Test
    void should_throw_when_brandId_is_not_positive() {
        assertThatThrownBy(() -> new PriceQuery(0L, 35455L, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("brandId must be positive");
    }

    @Test
    void should_throw_when_productId_is_not_positive() {
        assertThatThrownBy(() -> new PriceQuery(1L, 0L, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("productId must be positive");
    }

    @Test
    void should_throw_when_applicationDate_is_null() {
        assertThatThrownBy(() -> new PriceQuery(1L, 35455L, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("applicationDate is required");
    }
}
