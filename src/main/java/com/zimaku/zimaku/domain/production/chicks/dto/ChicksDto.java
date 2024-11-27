package com.zimaku.zimaku.domain.production.chicks.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.Date;


public record ChicksDto(
        Long id,
        @NotNull
        @Positive(message = "Number of males should always be positive")
        @Digits(integer = 3, fraction = 0)
        Integer males,
        @NotNull
        @Positive(message = "Number of females should always be positive")
        @Digits(integer = 3, fraction = 0)
        Integer females,
        @NotNull(message = "Number of fatalities should always be positive")
        @Digits(integer = 3, fraction = 0)
        Integer fatalities,
        @NotNull(message = "Batch number should be provided")
        @NotEmpty(message = "Batch number cannot be empty")
        String batch,
        Date date) {
}
