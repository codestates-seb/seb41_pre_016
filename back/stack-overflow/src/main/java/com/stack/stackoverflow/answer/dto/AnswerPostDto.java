package com.stack.stackoverflow.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerPostDto {

    private Long questionId;
    @NotBlank
    private String content;
}
