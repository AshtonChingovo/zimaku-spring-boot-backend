package com.zimaku.zimaku.domain.sales.orders.dto;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto{
        Long id;
        String date;
        @NotNull(message = "collection Date should be provided")
        String collectionDate;
        @NotNull(message = "quantity should be provided")
        Integer quantity;
        @NotNull(message = "is order paid status should be provided")
        Boolean isPaid;
        String comments;
        @NotNull(message = "client should be provided")
        ClientDto client;
        PriceDto price;
}
