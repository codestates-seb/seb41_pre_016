package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.UserPage.dto.UserPageResponseDto;
import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.question.dto.page.QuestionPageResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.dto.page.UsersPageResponseDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

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
    public ResponseEntity postUser(@Valid @RequestBody UserPostDto userPostDto, Question question) {
        User user = userService.createUser(mapper.userPostDtoToUser(userPostDto));

//        UserResponseDto userResponseDto = mapper.usersToUserResponseDto(users);

        return new ResponseEntity<>(mapper.usersToUserResponsesDto(user, question), HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId, Question question){
        User user = userService.findUser(userId);

        UserResponseDto userResponseDto = mapper.usersToUserResponsesDto(user, question);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                             @Positive @RequestParam int size) {
        Page<User> pageUsers = userService.findUsers(page - 1, size);
        List<User> users = pageUsers.getContent();

        return new ResponseEntity<>(
                new UsersPageResponseDto<>(mapper.usersToUserResponseDto(users),
                        pageUsers),
                HttpStatus.OK
        );
    }
//    @GetMapping("/login")
//    public ResponseEntity getLogin(@Valid @RequestBody LoginDto loginDto) {
//        User user = userService.login(mapper.loginDtoToUser(loginDto));
//
//        UserResponseDto userResponseDto = mapper.userToUserResponseDto(user);
//
//        return new ResponseEntity<>(
//                userResponseDto,
//                HttpStatus.OK
//        );
//    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId){
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
