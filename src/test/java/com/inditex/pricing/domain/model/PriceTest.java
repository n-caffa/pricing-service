package com.inditex.pricing.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PriceTest {

    @Test
    void should_throw_when_endDate_is_before_startDate() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 10, 0);
        LocalDateTime end = LocalDateTime.of(2020, 6, 14, 9, 0);

        assertThatThrownBy(() -> new Price(
                35455L,
                1L,
                1,
                start,
                end,
                0,
                new BigDecimal("35.50"),
                "EUR"
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("endDate must be after startDate");
    }

    @Test
    void should_throw_when_currency_is_blank() {
        assertThatThrownBy(() -> new Price(
                35455L,
                1L,
                1,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                0,
                new BigDecimal("35.50"),
                "   "
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency must not be blank");
    }
}
