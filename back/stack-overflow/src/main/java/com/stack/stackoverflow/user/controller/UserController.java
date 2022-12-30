package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.auth.jwt.JwtTokenizer;
import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user, userService.findTags(user));

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId) {
        User user = userService.findUser(userId);

        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user, userService.findTags(user));

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // header의 토큰값을 이용해서 User 정보 response
    @GetMapping
    public ResponseEntity getUser(HttpServletRequest request) {
        User user = userService.findclaims(request);
        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user, userService.findTags(user));

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<User> pageTags = userService.findPageUsers(page - 1, size);
        List<User> userList = pageTags.getContent();
        List<UserResponseDto> userResponseDto = mapper.usersToUserResponseDto(userList);

        List<Set<String>> tags = userService.findTags(userList);
        for(int i=0; i<userResponseDto.size(); i++) {
            userResponseDto.get(i).setTags(tags.get(i));
        }

        return new ResponseEntity<>(
                new UserDto<>(userResponseDto, pageTags),
                HttpStatus.OK);
    }

    // header의 토큰값을 가지고 있는 user logout
    @GetMapping("/logout")
    public ResponseEntity logoutUser(HttpServletRequest request) {
        userService.logout(request);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}