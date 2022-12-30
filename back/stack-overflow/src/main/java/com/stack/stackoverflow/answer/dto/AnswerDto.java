package com.stack.stackoverflow.answer.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AnswerDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        private Long questionId;

        private Long userPageId;

        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private Long answerId;
        @NotSpace
        private String content;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class response {
        private Long answerId;

        @NotNull
        private String content;
        @Column(nullable = false)
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int votes;
        private Long userId;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Votes {
        private Long answerId;
        private int votes;

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }
    }
}