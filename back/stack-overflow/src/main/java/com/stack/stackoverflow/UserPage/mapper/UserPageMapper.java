package com.stack.stackoverflow.UserPage.mapper;

import com.stack.stackoverflow.UserPage.dto.UserPageRequestDto;
import com.stack.stackoverflow.UserPage.dto.UserPageResponseDto;
import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.question.dto.QuestionResponseDto;
import com.stack.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPageMapper {
    User userPageRequestDtoToUser(UserPageRequestDto userPageRequestDto);
    UserPageResponseDto userPageToUserPageResponseDto(User user,
                                                      List<AnswerDto.response> answers,
                                                      List<QuestionResponseDto.NoAnswer> questions);
}
