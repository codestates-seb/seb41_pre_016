package com.stack.stackoverflow.comment.service;

import com.stack.stackoverflow.comment.entity.Comment;
import com.stack.stackoverflow.comment.repository.CommentRepository;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());
        findComment.setContent(comment.getContent());
        findComment.setModifiedAt(LocalDateTime.now());

        return commentRepository.save(findComment);
    }

    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);

        commentRepository.delete(findComment);
    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return findComment;
    }
}
