package com.stack.stackoverflow.answer.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
public class AnswerResponseDto {
    private Long answerId;
    private Long questionId;
    @NotNull
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int vote;
}
