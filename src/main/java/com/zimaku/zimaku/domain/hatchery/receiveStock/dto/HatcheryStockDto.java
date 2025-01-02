package com.zimaku.zimaku.domain.hatchery.receiveStock.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HatcheryStockDto {
    Long id;
    String date;
    @NotNull(message = "Quantity received should be provided")
    Integer quantity;
    @NotNull(message = "Batch Number should be provided")
    String batchNumber;
    @NotNull(message = "Total dispatched should be provided")
    Integer totalDispatched;
    Integer difference;
    @NotNull(message = "Eggs Id should be provided")
    Integer eggsId;
}