package com.stack.stackoverflow.user.controller;

import com.stack.stackoverflow.UserPage.mapper.UserPageMapper;
import com.stack.stackoverflow.UserPage.service.UserPageService;
import com.stack.stackoverflow.answer.mapper.AnswerMapper;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.tag.service.TagService;
import com.stack.stackoverflow.user.dto.*;
import com.stack.stackoverflow.user.dto.page.*;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

    private final UserMapper userMapper;
    private final UserPageMapper userPageMapper;
    private final UserPageService userPageService;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    private final TagService tagService;

    public UserController(UserService userService,
                          UserMapper userMapper,
                          UserPageMapper userPageMapper,
                          UserPageService userPageService,
                          AnswerMapper answerMapper,
                          QuestionMapper questionMapper,
                          TagService tagService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userPageMapper = userPageMapper;
        this.userPageService = userPageService;
        this.answerMapper = answerMapper;
        this.questionMapper = questionMapper;
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserRequestDto.Post userPostDto) {
        User user = userService.createUser(userMapper.userPostDtoToUser(userPostDto));

        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUserPage(@Positive @PathVariable("user-id") long userId) {
        User user = userService.findUser(userId);

        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<User> pageUsers = userService.findUsers(page - 1, size);
        List<User> users = pageUsers.getContent();

        return new ResponseEntity<>(
                new UserPageResponseDto<>(userMapper.usersToUserResponseDto(users), pageUsers), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity getLogin(@Valid @RequestBody LoginDto loginDto) {
        User user = userService.login(userMapper.loginDtoToUser(loginDto));

        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);

        return new ResponseEntity<>(
                userResponseDto,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}