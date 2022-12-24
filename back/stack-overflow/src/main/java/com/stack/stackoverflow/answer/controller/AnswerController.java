package com.stack.stackoverflow.answer.controller;

import com.stack.stackoverflow.answer.dto.*;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.answer.mapper.AnswerMapper;
import com.stack.stackoverflow.answer.repository.AnswerRepository;
import com.stack.stackoverflow.answer.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/answer")
@Validated
@Slf4j
public class AnswerController {
    private final AnswerRepository answerRepository;


    private final AnswerService answerService;
    private final AnswerMapper mapper;

<<<<<<< HEAD
    public AnswerController(AnswerService answerService, AnswerMapper mapper,
                            AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.mapper = mapper;
        this.answerRepository = answerRepository;
    }

    @PostMapping("/{user-id}")
    public ResponseEntity postAnswer(@PathVariable("user-id") @Positive long userPageId, @Valid @RequestBody AnswerDto.Post requestBody) {
        requestBody.setUserPageId(userPageId);

        Answer answer =
                answerService.createAnswer(mapper.answerPostDtoToAnswer(requestBody));
        return new ResponseEntity<>(
                (mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId, @Valid @RequestBody AnswerDto.Patch requestBody) {
        requestBody.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer(mapper.answerPatchDtoToAnswer(requestBody));
//        System.out.println("answer 생성시간 : " + answer.getCreatedAt());

        AnswerDto.response response = mapper.answerToAnswerResponseDto(answer);
//        System.out.println("answer response 생성시간 : " + response.getCreatedAt());

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{answer-id}/upvote")
    public ResponseEntity postUpvote(@PathVariable("answer-id") @Positive long answerId, @Valid @RequestBody AnswerDto.Votes requestBody) {
       requestBody.setAnswerId(answerId);
       Answer answer =
               answerService.answerUpvote(mapper.answerVotePostDto(requestBody));

       return new ResponseEntity<>((mapper.answerVoteResponseDto(answer)),HttpStatus.CREATED);
    }

    @DeleteMapping("/{answer-id}/downvote")
    public ResponseEntity patchDownvote(@PathVariable("answer-id") @Positive long answerId) {
        answerService.answerDownvote(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    };
}
