package com.inditex.pricing.application.usecase;

import com.inditex.pricing.application.dto.PriceQuery;
import com.inditex.pricing.application.dto.PriceResponse;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;

import java.util.Objects;

public class GetApplicablePriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetApplicablePriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = Objects.requireNonNull(priceRepositoryPort, "priceRepositoryPort is required");
    }

    public PriceResponse execute(PriceQuery query) {
        Objects.requireNonNull(query, "query is required");

        Price price = priceRepositoryPort
                .findApplicablePrice(query.brandId(), query.productId(), query.applicationDate())
                .orElseThrow(() -> new PriceNotFoundException(
                        query.brandId(),
                        query.productId(),
                        query.applicationDate().toString()
                ));

        return new PriceResponse(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.amount(),
                price.currency()
        );
    }
}
