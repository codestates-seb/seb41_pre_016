package com.stack.stackoverflow.answer.repository;

import com.stack.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAnswersHighestVotes(Integer votes);
}
