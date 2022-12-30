package com.stack.stackoverflow.user.dto;

import com.stack.stackoverflow.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.LocalVariable;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
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