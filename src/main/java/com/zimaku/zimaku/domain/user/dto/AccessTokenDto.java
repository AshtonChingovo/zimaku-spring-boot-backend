package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessTokenDto {
    private String jwtToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
