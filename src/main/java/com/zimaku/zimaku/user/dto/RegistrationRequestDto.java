package com.zimaku.zimaku.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegistrationRequestDto {
    @NotNull
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull
    @NotBlank(message = "Firstname cannot be blank")
    private String firstname;
    @NotNull
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @NotNull
    @NotEmpty(message = "Password cannot be blank")
    private String password;
}
