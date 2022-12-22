package com.stack.stackoverflow.answer.controller;

import com.stack.stackoverflow.answer.dto.AnswerPatchDto;
import com.stack.stackoverflow.answer.dto.AnswerPostDto;
import com.stack.stackoverflow.answer.dto.AnswerResponseDto;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.answer.mapper.AnswerMapper;
import com.stack.stackoverflow.answer.service.AnswerService;
import com.stack.stackoverflow.response.SingleResponseDto;
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

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {
        Answer answer =
                answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));
        return new ResponseEntity<>(
                (mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchAnswer(@PathVariable("id") long answerId, @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));
        System.out.println("answer 생성시간 : " + answer.getCreatedAt());

        AnswerResponseDto answerResponseDto = mapper.answerToAnswerResponseDto(answer);
        System.out.println("answer response 생성시간 : " + answerResponseDto.getCreatedAt());

        return new ResponseEntity<>(
                answerResponseDto,
                HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnswer(@PathVariable("id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
