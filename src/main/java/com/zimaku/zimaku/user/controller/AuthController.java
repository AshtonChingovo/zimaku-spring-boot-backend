package com.zimaku.zimaku.user.controller;

import com.zimaku.zimaku.user.dto.LoginRequestDto;
import com.zimaku.zimaku.user.dto.AuthResponseDto;
import com.zimaku.zimaku.user.dto.RegistrationRequestDto;
import com.zimaku.zimaku.user.dto.TokenRefreshResponseDto;
import com.zimaku.zimaku.user.service.RefreshTokenService;
import com.zimaku.zimaku.user.service.UserService;
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

    private UserService userService;
    private RefreshTokenService refreshTokenService;

    public AuthController(UserService userService, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto){
        userService.register(registrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponseDto> refreshToken(String refreshToken){
        return new ResponseEntity<>(refreshTokenService.refreshJwtToken(refreshToken), HttpStatus.OK);
    }
}
