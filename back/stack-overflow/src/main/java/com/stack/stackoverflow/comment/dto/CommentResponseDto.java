package com.stack.stackoverflow.comment.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
public class CommentResponseDto {
    private Long answerId;
    private Long commentId;
    @NotNull
    private String content;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
