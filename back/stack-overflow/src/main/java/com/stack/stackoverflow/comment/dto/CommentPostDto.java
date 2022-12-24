package com.stack.stackoverflow.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class CommentPostDto {
    private Long answerId;
    @NotBlank
    private String content;

}
