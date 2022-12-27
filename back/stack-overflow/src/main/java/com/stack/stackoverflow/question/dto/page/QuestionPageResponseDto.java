package com.stack.stackoverflow.question.dto.page;

import com.stack.stackoverflow.audit.Auditable;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
public class QuestionPageResponseDto<T> {
    private List<T> questions;
    private PageInfo pageInfo;

    public QuestionPageResponseDto(List<T> questions, Page page) {
        this.questions = questions;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
