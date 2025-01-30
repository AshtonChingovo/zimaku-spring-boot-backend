package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PasswordDto {
    private UUID id;
    private String password;
}
