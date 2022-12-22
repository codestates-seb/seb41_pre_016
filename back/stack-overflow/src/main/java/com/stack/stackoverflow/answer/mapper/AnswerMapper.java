package com.stack.stackoverflow.answer.mapper;

import com.stack.stackoverflow.answer.dto.AnswerPatchDto;
import com.stack.stackoverflow.answer.dto.AnswerPostDto;
import com.stack.stackoverflow.answer.dto.AnswerResponseDto;
import com.stack.stackoverflow.answer.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

}
