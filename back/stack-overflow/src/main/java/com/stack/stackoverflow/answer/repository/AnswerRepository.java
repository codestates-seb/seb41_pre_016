package com.stack.stackoverflow.answer.repository;

import com.stack.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
