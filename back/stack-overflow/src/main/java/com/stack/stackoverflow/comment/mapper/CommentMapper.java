package com.stack.stackoverflow.comment.mapper;

import com.stack.stackoverflow.comment.dto.CommentDto;
import com.stack.stackoverflow.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentDto.Post requestBody);
    Comment commentPatchDtoToComment(CommentDto.Patch requestBody);
    CommentDto.response commentToCommentResponseDto(Comment comment);
}
