package com.stack.stackoverflow.answer.controller;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.answer.mapper.AnswerMapper;
import com.stack.stackoverflow.answer.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/answer")
@Validated
//@Slf4j
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("/{user-id}")
    public ResponseEntity postAnswer(@PathVariable("user-id") @Positive long userId,
                                     @Valid @RequestBody AnswerDto.Post requestBody) {

        Answer answer =
                answerService.createAnswer(
                        mapper.answerPostDtoToAnswer(requestBody),
                        userId,
                        requestBody.getQuestionId());

        return new ResponseEntity<>(
                (mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch requestBody) {
        requestBody.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer(mapper.answerPatchDtoToAnswer(requestBody));

        AnswerDto.response response = mapper.answerToAnswerResponseDto(answer);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @PatchMapping("/{answer-id}/upvote")
    public ResponseEntity postUpvote(@PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.upAnswerVote(answerId);

        return new ResponseEntity<>((mapper.answerVoteResponseDto(answer)),HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}/downvote")
    public ResponseEntity patchDownvote(@PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.downAnswerVote(answerId);

        return new ResponseEntity<>((mapper.answerVoteResponseDto(answer)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
