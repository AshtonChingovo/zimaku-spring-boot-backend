package com.zimaku.zimaku.domain.sales.clients.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClientDto {
    private UUID id;
    private String date;
    @NotNull(message = "FirstName should be provided")
    private String firstName;
    private String lastName;
    private String address;
    @NotNull(message = "Phone number should be provided")
    private String phoneNumber;
    @NotNull(message = "Client type should be provided")
    private String clientType;
}

