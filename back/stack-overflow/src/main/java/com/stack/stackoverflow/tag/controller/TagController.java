package com.stack.stackoverflow.tag.controller;

import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.tag.dto.TagMultiResponseDto;
import com.stack.stackoverflow.tag.dto.TagSingleResponseDto;
import com.stack.stackoverflow.tag.entity.Tag;
import com.stack.stackoverflow.tag.mapper.TagMapper;
import com.stack.stackoverflow.tag.repository.TagRepository;
import com.stack.stackoverflow.tag.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Validated
@Slf4j
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final QuestionMapper questionMapper;

    public TagController(TagService tagService,
                         TagMapper tagMapper,
                         QuestionMapper questionMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
        this.questionMapper = questionMapper;
    }

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {

        Page<Tag> pageTags = tagService.findPageTags(page - 1, size);
        List<Tag> tagList = pageTags.getContent();

        return new ResponseEntity<>(
                new TagSingleResponseDto<>(tagMapper.tagToTagResponseDto(tagList),
                        pageTags),
                HttpStatus.OK);
    }

    @GetMapping("/{tag-id}")
    public ResponseEntity getTagsQuestions(@PathVariable("tag-id") @Positive long tagId) {
        List<Tag> tagList = tagService.findTag(tagId);
        List<Question> questionList = tagService.findQuestionByTagId(tagId);

        return new ResponseEntity<>(
                new TagMultiResponseDto<>(tagMapper.tagToTagResponseDto(tagList),
                        questionMapper.questionsToQuestionsDto(questionList)),
                HttpStatus.OK);
    }
}
