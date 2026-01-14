package com.inditex.pricing.infrastructure.in.rest;

import com.inditex.pricing.application.dto.PriceQuery;
import com.inditex.pricing.application.dto.PriceResponse;
import com.inditex.pricing.application.usecase.GetApplicablePriceUseCase;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
@Validated
public class PriceController {

    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/applicable")
    public PriceResponse getApplicablePrice(
            @RequestParam @Positive long brandId,
            @RequestParam @Positive long productId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        var query = new PriceQuery(brandId, productId, applicationDate);
        return useCase.execute(query);
    }
}
