package com.stack.stackoverflow.answer.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.Getter;

@Getter
public class AnswerPatchDto {
    private long answerId;
    @NotSpace
    private String content;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
