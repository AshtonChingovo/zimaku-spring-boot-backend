package com.zimaku.zimaku.domain.sales.clients.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientDto(
        UUID id,
        String date,
        @NotNull(message = "FirstName should be provided")
        String firstName,
        String lastName,
        String address,
        @NotNull(message = "Phone number should be provided")
        String phoneNumber,
        @NotNull(message = "Client type should be provided")
        String clientType
){}