package com.zimaku.zimaku.domain.production.dispatch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DispatchDto(
        Long id,
        @NotNull(message = "Total dispatched should be provided")
        @Positive(message = "Total dispatched should be larger than zero")
        Integer totalDispatched,
        @NotNull(message = "Batch number should be provided")
        @NotEmpty(message = "Batch number should be provided")
        String batchNumber) {
}
