package com.stack.stackoverflow.comment.controller;

import com.stack.stackoverflow.comment.dto.CommentDto;
import com.stack.stackoverflow.comment.entity.Comment;
import com.stack.stackoverflow.comment.mapper.CommentMapper;
import com.stack.stackoverflow.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/answer/{answer-id}/comment")
@Validated
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {

        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post requestBody) {
        Comment comment =
                commentService.createComment(mapper.commentPostDtoToComment(requestBody));

        return new ResponseEntity<>(
                (mapper.commentToCommentResponseDto(comment)),
                HttpStatus.CREATED);
    }
}
