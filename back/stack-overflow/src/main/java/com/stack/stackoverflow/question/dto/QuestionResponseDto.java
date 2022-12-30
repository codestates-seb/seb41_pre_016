package com.stack.stackoverflow.question.dto;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponseDto{
    @Getter
    @Setter
    @AllArgsConstructor
    public static class NoAnswer {
        @Positive
        private Long questionId;
        private String title;
        private String content;
        private int votes;
        private int views;
        private int answer_count;
        @Valid
        private List<String> tags;
        private Long userId;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class YesAnswer {
        @Positive
        private Long questionId;
        private String title;
        private String content;
        private int votes;
        private int views;
        private int answer_count;
        @Valid
        private List<String> tags;
        private Long userId;
        private String name;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<AnswerDto.response> answers;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private Long questionId;
        private int votes;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Count{
        private int question_count;
        private LocalDateTime createdAt;
    }
}
