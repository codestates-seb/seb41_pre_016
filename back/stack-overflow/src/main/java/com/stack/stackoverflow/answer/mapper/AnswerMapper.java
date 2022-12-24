package com.stack.stackoverflow.answer.mapper;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);
    Answer answerVotePostDto(AnswerDto.Votes requestBody);
    AnswerDto.response answerToAnswerResponseDto(Answer answer);
    AnswerDto.Votes answerVoteResponseDto(Answer answer);


}
