package com.zimaku.zimaku.domain.production.dispatch.dto;

import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DispatchDto {
    Long id;
    @NotNull
    Integer quantity;
    @NotNull
    String batchNumber;
    String date;
    @NotNull
    Integer totalStockReceived;
    String dateStockReceived;
    String ageOnDispatch;
    @NotNull
    Long eggsStockId;
}
