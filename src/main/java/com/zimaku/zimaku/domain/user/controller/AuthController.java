package com.zimaku.zimaku.domain.user.controller;

import com.zimaku.zimaku.domain.user.dto.AuthResponseDto;
import com.zimaku.zimaku.domain.user.dto.LoginRequestDto;
import com.zimaku.zimaku.domain.user.dto.RegistrationRequestDto;
import com.zimaku.zimaku.domain.user.dto.TokenRefreshResponseDto;
import com.zimaku.zimaku.domain.user.service.RefreshTokenService;
import com.zimaku.zimaku.domain.user.service.UserService;
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

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(UserService userService, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){
        userService.register(registrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponseDto> refreshToken(String refreshToken){
        return new ResponseEntity<>(refreshTokenService.refreshJwtToken(refreshToken), HttpStatus.OK);
    }
}
