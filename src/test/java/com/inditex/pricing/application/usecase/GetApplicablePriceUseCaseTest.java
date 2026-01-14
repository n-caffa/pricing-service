package com.inditex.pricing.application.usecase;

import com.inditex.pricing.application.dto.PriceQuery;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetApplicablePriceUseCaseTest {

    private final PriceRepositoryPort repo = mock(PriceRepositoryPort.class);
    private final GetApplicablePriceUseCase useCase = new GetApplicablePriceUseCase(repo);

    @Test
    void should_return_price_response_when_price_exists() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        var query = new PriceQuery(1L, 35455L, applicationDate);

        var price = new Price(
                35455L,
                1L,
                2,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30),
                1,
                new BigDecimal("25.45"),
                "EUR"
        );

        when(repo.findApplicablePrice(1L, 35455L, applicationDate)).thenReturn(Optional.of(price));

        var response = useCase.execute(query);

        assertThat(response.brandId()).isEqualTo(1L);
        assertThat(response.productId()).isEqualTo(35455L);
        assertThat(response.priceList()).isEqualTo(2);
        assertThat(response.startDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 15, 0));
        assertThat(response.endDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 18, 30));
        assertThat(response.price()).isEqualByComparingTo("25.45");
        assertThat(response.currency()).isEqualTo("EUR");

        verify(repo).findApplicablePrice(1L, 35455L, applicationDate);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void should_throw_when_no_price_exists() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        var query = new PriceQuery(1L, 35455L, applicationDate);

        when(repo.findApplicablePrice(1L, 35455L, applicationDate)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(query))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessageContaining("No applicable price found");

        verify(repo).findApplicablePrice(1L, 35455L, applicationDate);
        verifyNoMoreInteractions(repo);
    }
}
