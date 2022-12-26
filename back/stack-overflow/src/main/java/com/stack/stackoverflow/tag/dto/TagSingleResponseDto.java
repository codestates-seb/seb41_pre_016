package com.stack.stackoverflow.tag.dto;

import com.stack.stackoverflow.question.dto.page.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class TagSingleResponseDto<T> {
    private List<T> tags;

    public TagSingleResponseDto(List<T> tags) {
        this.tags = tags;
    }
}