package com.zimaku.zimaku.domain.user.service;

import com.zimaku.zimaku.domain.user.dto.AccountActiveDto;
import com.zimaku.zimaku.domain.user.dto.PasswordDto;
import com.zimaku.zimaku.domain.user.dto.UserDto;
import com.zimaku.zimaku.domain.user.entity.Role;
import com.zimaku.zimaku.domain.user.repository.RoleRepository;
import com.zimaku.zimaku.domain.user.repository.UserRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.exception.ResourceIdNotProvidedException;
import com.zimaku.zimaku.mapper.user.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static com.zimaku.zimaku.domain.util.StringUtil.DEFAULT_PASSWORD;
import static com.zimaku.zimaku.domain.util.StringUtil.ROLE_PREFIX;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public Page<UserDto> getUsers(int pageNumber, int pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort));
        return userRepository.findAll(page).map(userMapper::userToUserDto);
    }

    public void createUser(UserDto userDto){
        // setup account with default login password
        var password = passwordEncoder.encode(DEFAULT_PASSWORD);
        var user = userMapper.userDtoToUser(userDto);
        user.setPassword(password);

        Role role = roleRepository.findByTitleOneRole(ROLE_PREFIX + userDto.getRoles()
                .stream()
                .findFirst()
                .get().getTitle().toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find role requested"));

        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto){

        if(userDto.getId() == null)
            throw new ResourceIdNotProvidedException("User Id has not been provided");

        var user = userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find User with requested ID"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());

        // department & roles are not set on USER account updates
        // they are set when ADMIN is updating USER details
        if(userDto.getDepartment() != null)
            user.setDepartment(userDto.getDepartment());

        if(userDto.getRoles() != null)
            user.setDepartment(userDto.getDepartment());

        userRepository.save(user);

    }

    public void updatePassword(PasswordDto passwordDto){
        if(passwordDto.getId() == null || passwordDto.getId().toString().isEmpty())
            throw new ResourceIdNotProvidedException("User Id has not been provided");

        var user = userRepository.findById(passwordDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find User with requested ID"));

        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

        userRepository.save(user);
    }

    public void accountActivationStatus(AccountActiveDto accountActiveDto) {
        if(accountActiveDto == null || accountActiveDto.toString().isEmpty())
            throw new ResourceIdNotProvidedException("User Id has not been provided");

        var user = userRepository.findById(accountActiveDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find User with requested ID"));

        user.setActive(accountActiveDto.isActive());
        userRepository.save(user);
    }

    public UserDto getUser(UUID userId) {
        return userMapper.userToUserDto(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with provided ID not Found")));
    }
}
