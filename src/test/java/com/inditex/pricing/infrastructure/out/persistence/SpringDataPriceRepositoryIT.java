package com.inditex.pricing.infrastructure.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataPriceRepositoryIT {

    @Autowired
    private SpringDataPriceRepository repository;

    @Test
    void should_return_price_list_2_for_2020_06_14_16_00() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        var result = repository.findApplicable(1L, 35455L, applicationDate, PageRequest.of(0, 1));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPriceList()).isEqualTo(2);
        assertThat(result.get(0).getPrice()).isEqualByComparingTo("25.45");
        assertThat(result.get(0).getCurrency()).isEqualTo("EUR");
    }
}
