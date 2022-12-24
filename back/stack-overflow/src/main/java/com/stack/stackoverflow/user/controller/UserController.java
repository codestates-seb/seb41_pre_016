package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}