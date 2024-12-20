package com.zimaku.zimaku.domain.production.eggs.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class EggsDto{
         Long id;
         @NotNull(message = "Quantity should be provided")
         @Positive(message = "Quantity should always be larger than zero")
         Integer quantity;
         @NotNull(message = "Hatchable quantity should be provided")
         @Positive(message = "Hatchable should always be larger than zero")
         Integer hatchable;
         @NotNull(message = "Rejects should be provided")
         @Digits(integer = 3, fraction = 0)
         Integer rejects;
         @NotNull(message = "Batch number should be provided")
         @NotEmpty(message = "Batch number should be provided")
         String batchNumber;
         String age;
         String date;
}
