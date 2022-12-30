package com.stack.stackoverflow.tag.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagMultiResponseDto<T, R> {
    private List<T> tags;
    private List<R> questions;

    public TagMultiResponseDto(List<T> tags, List<R> questions) {
        this.tags = tags;
        this.questions = questions;
    }
}