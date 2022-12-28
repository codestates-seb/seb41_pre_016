package com.stack.stackoverflow.answer.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.service.UserPageService;
import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.answer.repository.AnswerRepository;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.service.QuestionService;
import com.stack.stackoverflow.user.entity.User;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserPageService userPageService;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository,
                         UserPageService userPageService,
                         QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.userPageService = userPageService;
        this.questionService = questionService;
    }

    public Answer createAnswer(Answer answer, Long userId, Long questionId) {
        // userPage 추가
        UserPage userPage = userPageService.findUserPage(userId);
        answer.setUserPage(userPage);

        // question 추가
        Question question = questionService.findQuestion(questionId);
        answer.setQuestion(question);

        // Question_Count 증가
        upAnswerCount(answer);

        return answerRepository.save(answer);
    }

    // Answer 수정
    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        findAnswer.setContent(answer.getContent());

        return answerRepository.save(findAnswer);
    }

    public void deleteAnswer(long answerId) {
        // answer 삭제
        Answer findAnswer = findVerifiedAnswer(answerId);

        // Question_Count 증가
        downAnswerCount(findAnswer);

        answerRepository.delete(findAnswer);
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer =
                answerRepository.findById(answerId);

        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

    public void findAnswerHighestVotes(int votes) {
        List<Answer> answer = answerRepository.findByVote(votes);
        answer.stream()
                .sorted(Comparator.comparing(Answer::getVote, Comparator.reverseOrder()))
                .map(Answer::getContent).forEach(System.out::println);
    }

    // answer_count 증가
    public void upAnswerCount(Answer answer) {
        // user의 answer_count 증가
        User user = answer.getUserPage().getUser();
        user.setAnswerCount(user.getAnswerCount() + 1);

        // question의 answer_count 증가
        Question question = answer.getQuestion();
        question.setAnswerCount(question.getAnswerCount() + 1);
    }

    // answer_count 감소
    public void downAnswerCount(Answer answer) {
        // user의 answer_count 감소
        User user = answer.getUserPage().getUser();
        user.setAnswerCount(user.getAnswerCount() - 1);

        // question의 answer_count 감소
        Question question = answer.getQuestion();
        question.setAnswerCount(question.getAnswerCount() - 1);
    }

    // Answer의 vote 수 증가
    public Answer upAnswerVote(Long answerId) {
        // Answer의 vote 수 증가
        Answer answer = findVerifiedAnswer(answerId);
        answer.setVote(answer.getVote() + 1);
        return answerRepository.save(answer);
    }

    // Answer의 vote 수 감소
    public Answer downAnswerVote(Long answerId) {
        // Answer의 vote 수 감소
        Answer answer = findVerifiedAnswer(answerId);
        answer.setVote(answer.getVote() - 1);
        return answerRepository.save(answer);
    }
}