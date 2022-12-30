package com.stack.stackoverflow.tag.controller;

import com.stack.stackoverflow.question.dto.QuestionResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.tag.dto.TagMultiResponseDto;
import com.stack.stackoverflow.tag.dto.TagResponseDto;
import com.stack.stackoverflow.tag.dto.TagSingleResponseDto;
import com.stack.stackoverflow.tag.entity.Tag;
import com.stack.stackoverflow.tag.mapper.TagMapper;
import com.stack.stackoverflow.tag.service.TagService;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Validated
@Slf4j
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;

    public TagController(TagService tagService,
                         TagMapper tagMapper,
                         QuestionMapper questionMapper,
                         UserMapper userMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
        this.questionMapper = questionMapper;
        this.userMapper = userMapper;

    }

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
        Page<Tag> pageTag = tagService.findPageTags(page - 1, size);
        List<Tag> tagList = tagService.findAll();

        //pageTag에서 받아온 List<Tag>에서 각 Tag의 QuestionCount를 지정하면 끝날거같아용


        return new ResponseEntity<>(
                new TagSingleResponseDto<>(tagMapper.tagToTagsResponseDto(tagList), pageTag),HttpStatus.OK);
    }

    @GetMapping("/{tag-id}")
    public ResponseEntity getTagsQuestions(@PathVariable("tag-id") @Positive long tagId) {
        List<Tag> tagList = tagService.findTag(tagId);
        List<Question> questionList = tagService.findQuestionByTagId(tagId);

        List<TagResponseDto> tagToTagResponseDto = tagMapper.tagToTagsResponseDto(tagList);
        tagToTagResponseDto.iterator().next().setQuestionCount(tagService.findQuestionByTagIdCount(tagId));


        return new ResponseEntity<>(
                new TagMultiResponseDto<>(tagToTagResponseDto,
                        questionMapper.questionsToQuestionsDto(questionList)), HttpStatus.OK);
    }
}
