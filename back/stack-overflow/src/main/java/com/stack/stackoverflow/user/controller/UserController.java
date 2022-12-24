package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.user.dto.UserDto;
import com.stack.stackoverflow.user.dto.UserPatchDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import com.stack.stackoverflow.util.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post requestBody) {
        User user = mapper.userPostToUser(requestBody);

        User createUser = userService.createUser(user);
        UserDto.Response response = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") @Positive long userId,
                                    @Valid @RequestBody UserDto.Patch requestBody){
        requestBody.setUserId(userId);

        User user = userService.updateUser(mapper.userPatchToUser(requestBody));

        UserDto.Response response = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId){
        User user = userService.findUser(userId);

        UserDto.Response response = mapper.userToUserResponseDto(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size){
        Page<User> pageUsers = userService.findUsers(page - 1, size);
        List<User> users = pageUsers.getContent();

        UserResponseDto userResponseDto = (UserResponseDto) mapper.usersToUserResponseDto(users);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId){
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
