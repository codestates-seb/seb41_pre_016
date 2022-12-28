package com.stack.stackoverflow.question.repository;


import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
