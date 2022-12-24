package com.stack.stackoverflow.question.controller;

import com.stack.stackoverflow.question.dto.QuestionRequestDto;
import com.stack.stackoverflow.question.dto.page.QuestionPageResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.question.repository.QuestionTagRepository;
import com.stack.stackoverflow.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/question")
@Validated
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService,
                              QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @PostMapping("/{user-id}")
    public ResponseEntity postQuestion(@Validated @RequestBody QuestionRequestDto.Post postDto,
                                       @PathVariable("user-id") @Positive long userId) {
        Question question = questionService.createQuestion(
                questionMapper.qusetionPostDtoToQuestion(postDto),
                postDto.getTags(),
                userId);
        System.out.println("question : " + question.getCreatedAt());
        return new ResponseEntity<>(
                questionMapper.questionToQuestionNoAnswerDto(question),
                HttpStatus.CREATED);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.findQuestionView(questionId);



        return new ResponseEntity<>(
                questionMapper.questionToQuestionYesAnswerDto(question),
                HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity getQuestionsByDate(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new QuestionPageResponseDto<>(questionMapper.questionsToQuestionsDto(questions),
                        pageQuestions),
                HttpStatus.OK
        );
    }

    @GetMapping("/no-answer")
    public ResponseEntity getQuestionsByNoAnswer(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = questionService.filterByAnswerCount(pageQuestions.getContent());

        return new ResponseEntity<>(
                new QuestionPageResponseDto<>(questionMapper.questionsToQuestionsDto(questions),
                        pageQuestions),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(
            @PathVariable("question-id") @Positive long questionId,
            @Valid @RequestBody QuestionRequestDto.Patch patchDto) {
        patchDto.setQuestionId(questionId);
        Question question = questionService.updateQuestion(
                questionMapper.questionPatchDtoToQuestion(patchDto),
                patchDto.getTags());

        return new ResponseEntity<>(
                questionMapper.questionToQuestionNoAnswerDto(question),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{question-id}/upvote")
    public ResponseEntity patchQuestionUpVote(
            @PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.upVote(questionId);

        return new ResponseEntity<>(
                questionMapper.questionToQuestionPatchDto(question),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{question-id}/downvote")
    public ResponseEntity patchQuestiondownVote(
            @PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.downVote(questionId);

        return new ResponseEntity<>(
                questionMapper.questionToQuestionPatchDto(question),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
