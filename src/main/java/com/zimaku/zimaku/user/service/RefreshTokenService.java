package com.zimaku.zimaku.user.service;

import com.zimaku.zimaku.exception.TokenNotFoundException;
import com.zimaku.zimaku.security.JwtHelper;
import com.zimaku.zimaku.user.dto.TokenRefreshResponseDto;
import com.zimaku.zimaku.user.entity.RefreshToken;
import com.zimaku.zimaku.user.repository.RefreshTokenRepository;
import com.zimaku.zimaku.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public TokenRefreshResponseDto refreshJwtToken(String token){
        var refreshToken = refreshTokenRepository
                .findByRefreshToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Refresh token not found"));

        if(isRefreshTokenValid(refreshToken)){
            var user = userRepository
                    .findById(refreshToken.getUser().getId())
                    .orElseThrow(() -> new UsernameNotFoundException("Failed to find user with this refresh token"));

            var jwtToken = new JwtHelper().generateToken(user.getEmail());

            return TokenRefreshResponseDto
                    .builder()
                    .jwtToken(jwtToken)
                    .refreshToken(token)
                    .build();
        }
        else{
            // remove invalid token from DB
            refreshTokenRepository.delete(refreshToken);
        }

        throw new TokenNotFoundException("Refresh token not found");
    }

    public RefreshToken createRefreshToken(UUID userId){
        var refreshToken = RefreshToken
                .builder()
                .refreshToken(UUID.randomUUID().toString())
                .user(userRepository.findById(userId).orElseThrow())
                .expiryDate(Instant.now().plusSeconds(120000))
                .build();

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public Boolean isRefreshTokenValid(RefreshToken refreshToken) {
        return true;
    }

}
