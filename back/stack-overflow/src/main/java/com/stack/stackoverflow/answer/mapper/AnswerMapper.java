package com.stack.stackoverflow.answer.mapper;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);

    List<AnswerDto.response> answersToAnswerResponseDtos(List<Answer> answer);

    default AnswerDto.response answerToAnswerResponseDto(Answer answer) {
        AnswerDto.response response = new AnswerDto.response(
                answer.getAnswerId(),
                answer.getContent(),
                answer.getCreatedAt(),
                answer.getModifiedAt(),
                answer.getVote(),
                answer.getUserPage().getUser().getUserId(),
                answer.getUserPage().getUser().getName()
        );

        return response;
    }

    default AnswerDto.Votes answerVoteResponseDto(Answer answer) {
        AnswerDto.Votes response = new AnswerDto.Votes(
                answer.getAnswerId(),
                answer.getVote()
        );

        return response;
    }

}