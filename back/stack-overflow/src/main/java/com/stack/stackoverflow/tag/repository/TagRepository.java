package com.stack.stackoverflow.tag.repository;

import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
