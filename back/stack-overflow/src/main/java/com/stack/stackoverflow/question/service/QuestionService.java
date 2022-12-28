package com.stack.stackoverflow.question.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.service.UserPageService;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.question.repository.QuestionRepository;
import com.stack.stackoverflow.question.repository.QuestionTagRepository;
import com.stack.stackoverflow.tag.service.TagService;
import com.stack.stackoverflow.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagService tagService;
    private final QuestionTagRepository questionTagRepository;
    private final UserPageService userPageService;

    public QuestionService(QuestionRepository questionRepository,
                           TagService tagService,
                           QuestionTagRepository questionTagRepository,
                           UserPageService userPageService) {
        this.questionRepository = questionRepository;
        this.tagService = tagService;
        this.questionTagRepository = questionTagRepository;
        this.userPageService = userPageService;
    }

    // Question 생성
    public Question createQuestion(Question question, List<String> tags, Long userId) {
        // QuestinTag 추가
        List<QuestionTag> questionTags = tagService.makeQuestionTags(tagService.findTags(tags));
        for(QuestionTag questionTag : questionTags) {
            questionTag.setQuestion(question);
        }
        question.setQuestionTags(questionTags);

        // userPage 추가
        UserPage userPage = userPageService.findUserPage(userId);
        question.setUserPage(userPage);

        // Question_Count 증가
        upQuestionCount(question);

        return questionRepository.save(question);
    }

    // Question 수정
    public Question updateQuestion(Question question, List<String> tags) {
        Question findQuestion = findQuestion(question.getQuestionId());

        // 제목 수정
        Optional.ofNullable(findQuestion.getTitle())
                .ifPresent(title -> findQuestion.setTitle(question.getTitle()));
        // 내용 수정
        Optional.ofNullable(findQuestion.getContent())
                .ifPresent(content -> findQuestion.setContent(question.getContent()));

        // 수정 전 태그들
        List<String> beforeTags = findQuestion.getQuestionTags().stream()
                .map(questionTag -> questionTag.getTag().getName())
                .collect(Collectors.toList());
        // 수정할 태그들
        List<String> afterTags = tags;

        for(String tag : beforeTags) {
            if (afterTags.contains(tag)) { // 공통된 태그
                afterTags.remove(tag);

            } else { // 사라진 태그
                for (QuestionTag questionTag : findQuestion.getQuestionTags()) {
                    if (questionTag.getTag() == tagService.verifyExistsTag(tag).get()) {
                        findQuestion.deleteQuestionTag(questionTag);
                        questionTagRepository.delete(questionTag);
                        break;
                    }
                }
            }
        }
        // 새로 생성된 태그
        List<QuestionTag> questionTags = tagService.makeQuestionTags(tagService.findTags(tags));
        for(QuestionTag questionTag : questionTags) {
            questionTag.setQuestion(question);
            findQuestion.getQuestionTags().add(questionTag);
            questionTagRepository.save(questionTag);
        }

        return questionRepository.save(findQuestion);
    }

    // Question 삭제
    public void deleteQuestion(long questionId) {
        Question question = findQuestion(questionId);

        downQuestionCount(question);
        questionRepository.delete(question);
    }

    // Question ID로 Question 조회
    public Question findQuestion(long questionId) {
        // Question 조회
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return question;
    }

    // Question 조회하기(+ 조회수 증가)
    public Question findQuestionView(long questionId) {
        Question question = findQuestion(questionId);
        question.setView(question.getView() + 1); // 조회수 증가

        return questionRepository.save(question);
    }

    // 가장 최신순으로 Question 정렬
    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }

    // answer_count == 0만 filter
    public List<Question> filterByAnswerCount(List<Question> questions) {
        return questions.stream().filter(question -> question.getAnswerCount() == 0)
                .collect(Collectors.toList());
    }

    // question_count 증가
    public void upQuestionCount(Question question) {
        User user = question.getUserPage().getUser();
        user.setQuestionCount(user.getQuestionCount() + 1);
    }

    // question_count 감소
    public void downQuestionCount(Question question) {
        User user = question.getUserPage().getUser();
        user.setQuestionCount(user.getQuestionCount() - 1);
    }

    // Question의 vote 수 증가
    public Question upQuestionVote(Long questionId) {
        Question question = findQuestion(questionId);
        question.setVote(question.getVote() + 1);
        return questionRepository.save(question);
    }

    // Question의 vote 수 감소
    public Question downQuestionVote(Long questionId) {
        Question question = findQuestion(questionId);
        question.setVote(question.getVote() - 1);
        return questionRepository.save(question);
    }
}
