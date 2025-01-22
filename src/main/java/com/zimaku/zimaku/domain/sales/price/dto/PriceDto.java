package com.zimaku.zimaku.domain.sales.price.dto;

public record PriceDto(
        Double unitPrice,
        String date,
        String currency) {
}
