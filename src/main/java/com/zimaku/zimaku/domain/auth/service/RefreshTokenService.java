package com.zimaku.zimaku.domain.user.service;

import com.zimaku.zimaku.domain.user.dto.AuthResponseDto;
import com.zimaku.zimaku.domain.user.dto.LoginRequestDto;
import com.zimaku.zimaku.domain.user.dto.RegistrationRequestDto;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto loginRequestDto);

    void register(RegistrationRequestDto registrationRequestDto);

}
