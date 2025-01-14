package com.zimaku.zimaku.domain.sales.orders.dto;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderDto(
        Long id,
        @NotNull(message = "collection Date should be provided")
        String collectionDate,
        @NotNull(message = "quantity should be provided")
        Integer quantity,
        @NotNull(message = "is order paid status should be provided")
        Boolean isPaid,
        String comments,
        @NotNull(message = "client should be provided")
        Client client
){ }
