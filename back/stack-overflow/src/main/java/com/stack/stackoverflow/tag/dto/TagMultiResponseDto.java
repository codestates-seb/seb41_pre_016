package com.stack.stackoverflow.tag.dto;

import com.stack.stackoverflow.question.dto.page.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class TagMultiResponseDto<T, R> {
    private List<T> tags;
    private List<R> questions;

    public TagMultiResponseDto(List<T> tags, List<R> questions) {
        this.tags = tags;
        this.questions = questions;
    }
}