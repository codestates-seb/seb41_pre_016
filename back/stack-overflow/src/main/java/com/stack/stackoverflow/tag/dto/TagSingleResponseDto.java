package com.stack.stackoverflow.tag.dto;

import com.stack.stackoverflow.question.dto.page.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class TagSingleResponseDto<T> {
    private List<T> tags;

    private PageInfo pageInfo;

    public TagSingleResponseDto(List<T> tags, Page page) {
        this.tags = tags;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}