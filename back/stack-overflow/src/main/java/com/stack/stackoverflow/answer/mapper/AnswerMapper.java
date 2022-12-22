package com.stack.stackoverflow.answer.mapper;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.dto.AnswerPatchDto;
import com.stack.stackoverflow.answer.dto.AnswerPostDto;
import com.stack.stackoverflow.answer.dto.AnswerResponseDto;
import com.stack.stackoverflow.answer.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);
    AnswerDto.response answerToAnswerResponseDto(Answer answer);

}
