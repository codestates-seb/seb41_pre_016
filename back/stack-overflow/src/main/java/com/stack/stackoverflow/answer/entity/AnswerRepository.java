package com.stack.stackoverflow.answer.entity;

import com.stack.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
