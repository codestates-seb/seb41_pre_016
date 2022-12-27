package com.stack.stackoverflow.question.dto;

import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class QuestionRequestDto{
    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        @Valid
        private List<String> tags;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Patch{
        private Long questionId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        @Valid
        private List<String> tags;
    }
}