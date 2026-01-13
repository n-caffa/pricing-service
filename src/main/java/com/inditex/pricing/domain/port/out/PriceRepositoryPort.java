package com.inditex.pricing.domain.port.out;

import com.inditex.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> findApplicablePrice(long brandId, long productId, LocalDateTime applicationDate);

}
