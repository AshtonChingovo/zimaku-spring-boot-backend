package com.zimaku.zimaku.domain.sales.price.dto;

import lombok.Builder;

@Builder
public record PriceDto(
        Double unitPrice,
        String date,
        String currency) {
}
