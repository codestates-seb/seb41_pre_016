package com.stack.stackoverflow.answer.entity;

import com.stack.stackoverflow.question.controller.QuestionController;
import com.stack.stackoverflow.question.dto.QuestionRequestDto;
import com.stack.stackoverflow.question.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@Validated
public class AnswerController {
    private final AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Validated @RequestBody Answer answer) {
        Answer response = answerRepository.save(answer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
