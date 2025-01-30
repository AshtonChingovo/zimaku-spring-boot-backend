package com.zimaku.zimaku.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountActiveDto {
    private UUID id;
    private boolean isActive;
}
