package com.zimaku.zimaku.domain.user.dto;

import com.zimaku.zimaku.domain.user.entity.Role;
import lombok.Builder;
import lombok.Data;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String date;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String department;
    private boolean isActive;
    private Collection<Role> roles;
}
