package com.stack.stackoverflow.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerPostDto {

    private String questionId;
    @NotBlank
    private String content;
}
