package com.zimaku.zimaku.domain.user.entity;

import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@Entity(name="refreshtoken")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String refreshToken;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Instant expiryDate;

}
