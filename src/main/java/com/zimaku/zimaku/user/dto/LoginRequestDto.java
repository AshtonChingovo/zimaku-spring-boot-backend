package com.zimaku.zimaku.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequestDto {
    @NonNull
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NonNull
    @NotEmpty(message = "Password cannot be blank")
    private String password;
}
