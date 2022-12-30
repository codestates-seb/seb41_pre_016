package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.auth.jwt.JwtTokenizer;
import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

// @CrossOrigin(origins = "", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService,
                          UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserPostDto userPostDto) {
        User user = userService.createUser(mapper.userPostDtoToUser(userPostDto));

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId) {
        User user = userService.findUser(userId);

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUser(HttpServletRequest request) {
        Map<String, Object> claims = userService.findclaims(request);

        // assecc-token 또는 refresh-token을 통해서 claims를 추출할 수 없는 경우
        if(claims.get("sub") == null) return new ResponseEntity<>(claims, HttpStatus.BAD_REQUEST);

        // assecc-token 또는 refresh-token을 통해서 claims를 추출할 수 있는 경우
        User user = userService.findByEmail((String) claims.get("sub"));
        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}