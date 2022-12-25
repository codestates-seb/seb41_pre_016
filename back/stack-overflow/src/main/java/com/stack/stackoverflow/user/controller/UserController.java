package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

// @CrossOrigin(origins = "", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    private final UserService userService;

    private final UserMapper mapper;

    public UserController(UserService userService,
                          UserMapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserPostDto userPostDto) {
        User user =
                userService.createUser(mapper.userPostDtoToUser(userPostDto));

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId){
        User user = userService.findUser(userId);

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity getLogin(@Valid @RequestBody LoginDto loginDto) {
        User user = userService.login(mapper.loginDtoToUser(loginDto));

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId){
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}