package com.stack.stackoverflow.answer.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.Getter;

@Getter
public class AnswerPatchDto {
    private Long questionId;
    @NotSpace
    private String content;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
