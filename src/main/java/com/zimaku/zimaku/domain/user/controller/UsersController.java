package com.zimaku.zimaku.domain.user.controller;

import com.zimaku.zimaku.domain.user.dto.AccountActiveDto;
import com.zimaku.zimaku.domain.user.dto.PasswordDto;
import com.zimaku.zimaku.domain.user.dto.UserDto;
import com.zimaku.zimaku.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService usersService;

    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(usersService.getUsers(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") UUID userId){
        return new ResponseEntity<UserDto>(usersService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDto userDto){
        usersService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> putUser(@Valid @RequestBody UserDto userDto){
        usersService.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/update/password")
    public ResponseEntity<?> putPassword(@Valid @RequestBody PasswordDto passwordDto){
        usersService.updatePassword(passwordDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/active")
    public ResponseEntity<?> deactivateUserAccount(@Valid @RequestBody AccountActiveDto accountActiveDto){
        usersService.accountActivationStatus(accountActiveDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
