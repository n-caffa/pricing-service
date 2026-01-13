package com.inditex.pricing.application.usecase;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(long brandId, long productId, String applicationDate) {
        super("No applicable price found for brandId=%d, productId=%d, applicationDate=%s"
                .formatted(brandId, productId, applicationDate));
    }
}
