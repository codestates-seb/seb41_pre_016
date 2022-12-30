package com.stack.stackoverflow.tag.dto;

import com.stack.stackoverflow.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TagResponseDto {
    private Long tagId;
    private String name;
    private String content;
    private int questionCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
