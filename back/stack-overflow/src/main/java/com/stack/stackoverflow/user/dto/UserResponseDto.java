package com.stack.stackoverflow.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}