package com.zimaku.zimaku.domain.user.controller;

import com.zimaku.zimaku.domain.user.dto.AuthResponseDto;
import com.zimaku.zimaku.domain.user.dto.LoginRequestDto;
import com.zimaku.zimaku.domain.user.dto.RegistrationRequestDto;
import com.zimaku.zimaku.domain.user.dto.TokenRefreshResponseDto;
import com.zimaku.zimaku.domain.user.service.RefreshTokenService;
import com.zimaku.zimaku.domain.user.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){
        authService.register(registrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponseDto> refreshToken(String refreshToken){
        return new ResponseEntity<>(refreshTokenService.refreshJwtToken(refreshToken), HttpStatus.OK);
    }
}
