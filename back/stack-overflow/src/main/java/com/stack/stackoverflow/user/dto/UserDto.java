package com.stack.stackoverflow.user.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.stack.stackoverflow.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserDto{
    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @NotBlank
        @Email
        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=?<>:])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                message = "특수문자는 1개 이상 들어가야 합니다, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
        private String password;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private Long userId;

        @NotSpace(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @NotSpace(message = "비밀번호는 공백이 없어야 합니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=?<>:])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                message = "특수문자는 1개 이상 들어가야 합니다, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
        private String password;

        public void setUserId(long userId){
            this.userId = userId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response{

        private Long userId;

        private String name;

        private String email;

        private String password;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;
    }
}
