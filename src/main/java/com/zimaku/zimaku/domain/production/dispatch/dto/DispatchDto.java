package com.zimaku.zimaku.domain.production.dispatch.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DispatchDto{
    Long id;
    Integer quantity;
    String batchNumber;
    String date;
    Integer totalStockReceived;
    String dateStockReceived;
    String ageOnDispatch;
    Integer eggsId;
}
