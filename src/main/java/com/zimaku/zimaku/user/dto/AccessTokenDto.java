package com.zimaku.zimaku.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessTokenDto {
    private String jwtToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
