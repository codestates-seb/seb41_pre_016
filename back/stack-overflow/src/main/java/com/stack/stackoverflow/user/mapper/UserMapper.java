package com.stack.stackoverflow.user.mapper;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.user.dto.LoginDto;
import com.stack.stackoverflow.user.dto.UserPatchDto;
import com.stack.stackoverflow.user.dto.UserPostDto;
import com.stack.stackoverflow.user.dto.UserResponseDto;
import com.stack.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostDtoToUser(UserPostDto userPostDto);

    User userPatchDtoToUser(UserPatchDto userPatchDto);

    User loginDtoToUser(LoginDto loginDto);

    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> usersToUserResponseDto(List<User> users);

    default List<UserResponseDto> usersToUserResponseDtos(List<User> users) {
        List<String> tags = new ArrayList<>();
        user.getUserPage().getQuestions().stream().forEach(s -> s.getQuestionTags().stream().forEach(a -> tags.add(a.getTag().getName())));

       List<UserResponseDto> userResponsesDto = new List<UserResponseDto>(

                user.getName(),
                user.getEmail(),
                user.getPassword(),
                tags,
                user.getCreatedAt(),
                user.getModifiedAt()
        );

        return userResponsesDto;
    }

    default UserResponseDto usersToUserResponsesDto(User user, Question question) {
        List<String> tags = new ArrayList<>();
        user.getUserPage().getQuestions().stream().forEach(s -> s.getQuestionTags().stream().forEach(a -> tags.add(a.getTag().getName())));

        UserResponseDto userResponseDto = new UserResponseDto(
        user.getUserId(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
                tags,
        user.getCreatedAt(),
        user.getModifiedAt()
        );

        return userResponseDto;
    }
}

