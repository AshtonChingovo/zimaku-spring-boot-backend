package com.zimaku.zimaku.domain.sales.clients.model;

import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Client extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String clientType;
}
