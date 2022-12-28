package com.stack.stackoverflow.user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class UserResponseDto {

    private Long userId;

    private String name;

    private String email;

    private String password;

    @Valid
    private List<String> tags;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}