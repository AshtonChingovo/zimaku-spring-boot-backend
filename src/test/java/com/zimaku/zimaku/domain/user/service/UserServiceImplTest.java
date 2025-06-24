package com.zimaku.zimaku.domain.user.service;

import com.zimaku.zimaku.domain.user.dto.PasswordDto;
import com.zimaku.zimaku.domain.user.dto.UserDto;
import com.zimaku.zimaku.domain.user.entity.User;
import com.zimaku.zimaku.domain.user.repository.RoleRepository;
import com.zimaku.zimaku.domain.user.repository.UserRepository;
import com.zimaku.zimaku.exception.ResourceIdNotProvidedException;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    UserMapper mapper;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    UserDto userDtoNullId;
    UserDto userDto;
    User user;
    PasswordDto passwordDto;
    PasswordDto passwordDtoNullId;

    @BeforeEach
    void setUp(){

        userDtoNullId = UserDto.builder()
                .date("02/02/2025")
                .email("user@email.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0777888999")
                .address("John Doe home address")
                .department("Production")
                .isActive(true)
                .build();

        userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .date("02/02/2025")
                .email("user@email.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0777888999")
                .address("John Doe home address")
                .department("Production")
                .isActive(true)
                .build();

        user = User.builder()
                .email("user@email.com")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0777888999")
                .address("John Doe home address")
                .department("Production")
                .isActive(true)
                .build();

        passwordDto = PasswordDto.builder()
                .id(UUID.randomUUID())
                .password("password")
                .build();

        passwordDtoNullId = PasswordDto.builder()
                .password("password")
                .build();
    }

    @Test
    void testGetUser_WhenGetUsers_ShouldReturnPaginatedData(){

        Page<User> page = new PageImpl<>(List.of(user));

        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(mapper.userToUserDto(any(User.class))).thenReturn(userDtoNullId);

        var pagination = userServiceImpl.getUsers(0, 10, "id");

        assertEquals(1, pagination.getContent().size());
    }

    @Test
    void testUpdateUser_WhenUserDtoIdNotFound_ShouldThrowException(){

        assertThrows(ResourceIdNotProvidedException.class, () -> userServiceImpl.updateUser(userDtoNullId));

    }

    @Test
    void testUpdateUser_WhenUserIdNotFound_ShouldThrowException(){

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(userDto));

    }

    @Test
    void testUpdatePassword_WhenUpdatePasswordDtoIdIsNull_ShouldThrowException(){

        assertThrows(ResourceIdNotProvidedException.class, () -> userServiceImpl.updatePassword(passwordDtoNullId));

    }


    @Test
    void testUpdatePassword_WhenUserIdNotFound_ShouldThrowException(){

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updatePassword(passwordDto));

    }

}