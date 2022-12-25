package com.stack.stackoverflow.answer.service;

import com.stack.stackoverflow.answer.dto.AnswerDto;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.answer.repository.AnswerRepository;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        findAnswer.setContent(answer.getContent());
        findAnswer.setModifiedAt(LocalDateTime.now());

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);


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

    public Answer answerUpvote(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        int i = Integer.valueOf(answer.getVote());
        i = i + 1;

        return answerRepository.save(findAnswer);

    }

    //    public Answer answerDownvote(Answer answer) {
//        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
//        int i = Integer.valueOf(answer.getVotes().intValue());
//        i = i - 1;
//
//        return answerRepository.save(findAnswer);
//    }
    public Answer answerDownvote(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        int i = Integer.valueOf(answer.getVote());
        i = i - 1;

        return answerRepository.save(answer);
    }
}