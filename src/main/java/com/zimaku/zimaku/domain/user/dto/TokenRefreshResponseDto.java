package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponseDto {
    private String jwtToken;
    private String tokenType = "Bearer";
    private String refreshToken;

}
