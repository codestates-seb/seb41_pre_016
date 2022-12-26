package com.stack.stackoverflow.UserPage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserPageResponseDto<T, R> {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private int question_count;
    private int answer_count;
    private List<T> answers;
    private List<R> questions;
}
