package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthResponseDto {
    private UUID userId;
    private String token;
    private String tokenType;
    private String refreshToken;
}
