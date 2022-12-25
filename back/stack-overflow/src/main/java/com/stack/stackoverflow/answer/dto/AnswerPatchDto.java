package com.stack.stackoverflow.answer.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.Getter;

@Getter
public class AnswerPatchDto {

    private Long answerId;

    @NotSpace
    private String content;

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}