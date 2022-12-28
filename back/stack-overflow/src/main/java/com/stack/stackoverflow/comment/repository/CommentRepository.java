package com.stack.stackoverflow.comment.repository;

import com.stack.stackoverflow.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
