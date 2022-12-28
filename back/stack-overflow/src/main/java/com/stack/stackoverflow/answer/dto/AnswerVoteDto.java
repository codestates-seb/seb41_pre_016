package com.stack.stackoverflow.answer.dto;

import lombok.Getter;

@Getter
public class AnswerVoteDto {
    private Long answerId;
    private int votes;
}
