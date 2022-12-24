package com.stack.stackoverflow.user.mapper;

import com.stack.stackoverflow.user.dto.UserDto;
import com.stack.stackoverflow.user.dto.UserPatchDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostToUser(UserDto.Post requestBody);

    User userPatchToUser(UserDto.Patch requestBody);

    UserDto.Response userToUserResponseDto(User user);

    List<UserDto.Response> usersToUserResponseDto(List<User> users);
}
