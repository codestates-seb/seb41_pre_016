package com.stack.stackoverflow.comment.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.Getter;

@Getter
public class CommentPatchDto {
    private Long commentId;
    @NotSpace
    private String content;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
