package com.stack.stackoverflow.tag.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.question.repository.QuestionRepository;
import com.stack.stackoverflow.question.service.QuestionService;
import com.stack.stackoverflow.tag.entity.Tag;
import com.stack.stackoverflow.tag.repository.TagRepository;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public TagService(TagRepository tagRepository,
                      QuestionRepository questionRepository,
                      UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    // Tag가 테이블에 있으면 있는 Tag 반환
    // 없으면 Tag를 테이블에 생성해서 Tag 반환
    public Tag createTag(String name) {
        Optional<Tag> findTag = verifyExistsTag(name);

        if (findTag.isEmpty()) {
            Tag tag = new Tag();
            tag.setName(name);
            return tagRepository.save(tag);
        }

        return findTag.get();
    }


    // Tag의 name이 Tag 테이블에 있는지 확인
    public Optional<Tag> verifyExistsTag(String name) {
        Optional<Tag> findTag = tagRepository.findByName(name);
        return findTag;
    }

    // 모든 tag들 찾기
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    // tag의 name을 바탕으로 List<Tag> 생성
    public List<Tag> findTags(List<String> tags) {
        return tags.stream()
                .map(tag -> createTag(tag))
                .collect(Collectors.toUnmodifiableList());
    }

    public Page<Tag> findPageTags(int page, int size){
        return tagRepository.findAll(PageRequest.of(page, size,
                Sort.by("tagId").descending()));
    }

    // tag-id를 이용해서 Tag 반환
    public List<Tag> findTag(Long tagId) {
        List<Tag> tags = new ArrayList<>();

        Optional<Tag> optionalTag =
                tagRepository.findById(tagId);

        Tag tag =
                optionalTag.orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);
                });

        tags.add(tag);
        return tags;
    }

    // tagId를 기준으로 List<Question> 가져오기
    public List<Question> findQuestionByTagId(Long tagId) {
        List<Question> questions = questionRepository.findAll();
        List<Question> findQuestions = new ArrayList<>();


        for (Question question : questions) {
            int ok = 0;
            for (QuestionTag questionTag : question.getQuestionTags()) {
                if (questionTag.getTag().getTagId() == tagId) ok++;
            }
            if (ok != 0) findQuestions.add(question);
        }

        return findQuestions;
    }

    // tagId를 기준으로 List<Question> 가져와 갯수로 바꾸기
    public int findQuestionByTagIdCount(Long tagId) {
        List<Question> questions = questionRepository.findAll();
        List<Question> findQuestions = new ArrayList<>();

        for (Question question : questions) {
            int ok = 0;
            for (QuestionTag questionTag : question.getQuestionTags()) {
                if (questionTag.getTag().getTagId() == tagId) ok++;
            }
            if (ok != 0) findQuestions.add(question);
        }
        return findQuestions.size();
    }


    // List<Tag>를 바탕으로 List<QuestionTag> 생성
    public List<QuestionTag> makeQuestionTags(List<Tag> tags) {
        List<QuestionTag> questionTags = new ArrayList<>();
        for (Tag tag : tags) {
            QuestionTag questionTag = new QuestionTag();
            questionTag.setTag(tag);
            questionTags.add(questionTag);
        }

        return questionTags;
    }
}
