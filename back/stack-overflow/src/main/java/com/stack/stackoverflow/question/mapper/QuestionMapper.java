package com.stack.stackoverflow.question.mapper;

import com.stack.stackoverflow.question.dto.QuestionRequestDto;
import com.stack.stackoverflow.question.dto.QuestionResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question qusetionPostDtoToQuestion(QuestionRequestDto.Post questPostDto);

    Question questionPatchDtoToQuestion(QuestionRequestDto.Patch questPatchDto);

    List<QuestionResponseDto.NoAnswer> questionsToQuestionsDto(List<Question> questions);

    default QuestionResponseDto.NoAnswer questionToQuestionNoAnswerDto(Question question) {
        QuestionResponseDto.NoAnswer questionResponseDto = new QuestionResponseDto.NoAnswer(
                question.getQuestionId(),
                question.getTitle(),
                question.getContent(),
                question.getVote(),
                question.getView(),
                question.getAnswerCount(),
                question.getQuestionTags().stream().map(questionTag ->
                        questionTag.getTag().getName()
                ).collect(Collectors.toUnmodifiableList()),
                question.getUserPage().getUser().getUserId(),
                question.getUserPage().getUser().getName(),
                question.getCreatedAt(),
                question.getModifiedAt()
        );

        return questionResponseDto;
    }

    default QuestionResponseDto.YesAnswer questionToQuestionYesAnswerDto(Question question) {
        QuestionResponseDto.YesAnswer questionResponseDto = new QuestionResponseDto.YesAnswer(
                question.getQuestionId(),
                question.getTitle(),
                question.getContent(),
                question.getVote(),
                question.getView(),
                question.getAnswerCount(),
                question.getQuestionTags().stream().map(questionTag ->
                        questionTag.getTag().getName()
                ).collect(Collectors.toUnmodifiableList()),
                question.getUserPage().getUser().getUserId(),
                question.getUserPage().getUser().getName(),
                question.getCreatedAt(),
                question.getModifiedAt(),
                question.getAnswers()
        );

        return questionResponseDto;
    }

    default QuestionResponseDto.Patch questionToQuestionPatchDto(Question question) {
        QuestionResponseDto.Patch patch = new QuestionResponseDto.Patch(
                question.getQuestionId(),
                question.getVote()
        );

        return patch;
    }
}