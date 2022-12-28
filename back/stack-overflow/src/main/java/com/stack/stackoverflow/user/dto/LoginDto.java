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
    private String email;
    private String password;
}
