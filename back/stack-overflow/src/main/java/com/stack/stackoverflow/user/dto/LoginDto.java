package com.stack.stackoverflow.user.dto;

import com.stack.stackoverflow.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginDto {
    private Long userId;

    @NotBlank
    @Email
    private String email;

    @NotSpace(message = "비밀번호는 공백이 없어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=?<>:])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "특수문자는 1개 이상 들어가야 합니다, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
    private String password;
}
