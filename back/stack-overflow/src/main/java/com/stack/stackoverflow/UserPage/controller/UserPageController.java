package com.stack.stackoverflow.UserPage.controller;

import com.stack.stackoverflow.UserPage.dto.UserPageRequestDto;
import com.stack.stackoverflow.UserPage.dto.UserPageResponseDto;
import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.mapper.UserPageMapper;
import com.stack.stackoverflow.UserPage.service.UserPageService;
import com.stack.stackoverflow.answer.mapper.AnswerMapper;
import com.stack.stackoverflow.question.dto.page.QuestionPageResponseDto;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/user-page")
@Validated
@Slf4j
public class UserPageController {
    private final UserPageService userPageService;
    private final UserService userService;
    private final UserPageMapper userPageMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;

    public UserPageController(UserPageService userPageService,
                              UserService userService,
                              UserPageMapper userPageMapper,
                              QuestionMapper questionMapper,
                              AnswerMapper answerMapper,
                              UserMapper userMapper) {
        this.userPageService = userPageService;
        this.userService = userService;
        this.userPageMapper = userPageMapper;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
        this.userMapper = userMapper;
    }


    @GetMapping("/{user-id}")
    public ResponseEntity getUserPage(@Positive @PathVariable("user-id") long userId) {
        UserPage userPage = userPageService.findUserPage(userId);
        UserPageResponseDto userPageResponseDto = userPageMapper.userPageToUserPageResponseDto(
                userPage.getUser(),
                answerMapper.answersToAnswerResponseDtos(userPage.getAnswers()),
                questionMapper.questionsToQuestionsDto(userPage.getQuestions())
        );
        userPageResponseDto.setAnswer_count(userPage.getUser().getAnswerCount());
        userPageResponseDto.setQuestion_count(userPage.getUser().getQuestionCount());

        return new ResponseEntity<>(userPageResponseDto, HttpStatus.OK);
    }

    // User 정보 업데이트
    @PatchMapping("/{user-id}")
    public ResponseEntity patchUserPage(@Positive @PathVariable("user-id") long userId,
                                      @Valid @RequestBody UserPageRequestDto userPageRequestDto) {

        // 유효한 UserPage인지 검증
        userPageRequestDto.setUserId(userId);
        UserPage userPage = userPageService.updateUserPage(
                userPageMapper.userPageRequestDtoToUser(userPageRequestDto));

        // 응답
        UserPageResponseDto userPageResponseDto = userPageMapper.userPageToUserPageResponseDto(
                userPage.getUser(),
                answerMapper.answersToAnswerResponseDtos(userPage.getAnswers()),
                questionMapper.questionsToQuestionsDto(userPage.getQuestions())
        );
        userPageResponseDto.setAnswer_count(userPage.getUser().getAnswerCount());
        userPageResponseDto.setQuestion_count(userPage.getUser().getQuestionCount());

//        // 유효한 UserPage인지 검증
//        UserPage userPage = userPageService.findUserPage(userId);
//
//        // User 정보 업데이트
//        userPageRequestDto.setUserId(userId);
//        User user = userService.updateUser(userPageMapper.userPageRequestDtoToUser(userPageRequestDto));
//        userPage.setUser(user);
//
//        // 응답
//        UserPageResponseDto userPageResponseDto = userPageMapper.userPageToUserPageResponseDto(
//                userPage.getUser(),
//                answerMapper.answersToAnswerResponseDtos(userPage.getAnswers()),
//                questionMapper.questionsToQuestionsDto(userPage.getQuestions())
//        );
//        userPageResponseDto.setAnswer_count(userPage.getUser().getAnswerCount());
//        userPageResponseDto.setQuestion_count(userPage.getUser().getQuestionCount());

        return new ResponseEntity<>(userPageResponseDto, HttpStatus.OK);
    }
}