package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccessTokenDto {
    private String jwtToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
