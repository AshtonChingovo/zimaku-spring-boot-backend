package com.zimaku.zimaku.domain.user.service;

import com.zimaku.zimaku.domain.user.entity.User;
import com.zimaku.zimaku.domain.user.repository.RoleRepository;
import com.zimaku.zimaku.exception.UsernameAlreadyExistsException;
import com.zimaku.zimaku.security.JwtHelper;
import com.zimaku.zimaku.domain.user.dto.LoginRequestDto;
import com.zimaku.zimaku.domain.user.dto.AuthResponseDto;
import com.zimaku.zimaku.domain.user.dto.RegistrationRequestDto;
import com.zimaku.zimaku.domain.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService,
                       AuthenticationManager authenticationManager,
                       JwtHelper jwtHelper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()));

        var userId = userRepository
                .findByEmail(loginRequestDto.getEmail())
                .map(User::getId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", loginRequestDto.getEmail())));

        var refreshToken = refreshTokenService
                .createRefreshToken(userId)
                .getRefreshToken();

        return AuthResponseDto.builder()
                .token(jwtHelper.generateToken(loginRequestDto.getEmail()))
                .tokenType("Bearer")
                .refreshToken(refreshToken)
                .build();
    }

    public void register(RegistrationRequestDto registrationRequestDto){
        userRepository.findByEmail(registrationRequestDto.getEmail()).ifPresent( entity -> {
            throw new UsernameAlreadyExistsException(String.format("%s already exists, please use another one", registrationRequestDto.getEmail()));
        });

        userRepository.save(User.builder()
                .email(registrationRequestDto.getEmail())
                .firstname(registrationRequestDto.getFirstname())
                .surname(registrationRequestDto.getSurname())
                .password(registrationRequestDto.getPassword())
                .isActive(true)
                .roles(roleRepository.findByTitle("ROLE_USER"))
                .build());
    }

}
