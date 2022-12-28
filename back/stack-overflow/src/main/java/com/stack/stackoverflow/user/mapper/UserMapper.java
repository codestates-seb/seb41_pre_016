package com.stack.stackoverflow.user.mapper;

import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserRequestDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostDtoToUser(UserRequestDto.Post userPostDto);

    User userPatchDtoToUser(UserRequestDto.Patch userPatchDto);

    User loginDtoToUser(LoginDto loginDto);

    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> usersToUserResponseDto(List<User> users);
}