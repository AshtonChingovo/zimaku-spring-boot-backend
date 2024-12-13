package com.zimaku.zimaku.domain.user.repository;

import com.zimaku.zimaku.domain.user.entity.RefreshToken;
import com.zimaku.zimaku.domain.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Modifying
    @Transactional
    @Query("DELETE FROM refreshtoken rt WHERE rt.user.id = :userId")
    void deleteRefreshToken(@Param("userId") UUID userId);

}
