package com.zimaku.zimaku.domain.user.service;

import com.zimaku.zimaku.domain.user.repository.UserRepository;
import com.zimaku.zimaku.exception.TokenNotFoundException;
import com.zimaku.zimaku.domain.user.dto.TokenRefreshResponseDto;
import com.zimaku.zimaku.domain.user.entity.RefreshToken;
import com.zimaku.zimaku.domain.user.repository.RefreshTokenRepository;
import com.zimaku.zimaku.security.JwtHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

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

        // delete any existing refresh token from older logins
        refreshTokenRepository.deleteRefreshToken(userRepository.findById(userId).orElseThrow().getId());

        var refreshToken = RefreshToken
                .builder()
                .refreshToken(userId.toString())
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
