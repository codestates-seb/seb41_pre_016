package com.stack.stackoverflow.answer.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class AnswerResponseDto {
    private Long answerId;
    private Long userId;
    @NotNull
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int votes;
}