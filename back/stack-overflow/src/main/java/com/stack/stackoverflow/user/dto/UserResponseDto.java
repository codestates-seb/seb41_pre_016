package com.stack.stackoverflow.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;

    private String name;

    private String email;

    private String password;

    private Set<String> tags = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}