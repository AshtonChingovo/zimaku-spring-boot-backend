package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    private String token;
    private String tokenType;
    private String refreshToken;
}
