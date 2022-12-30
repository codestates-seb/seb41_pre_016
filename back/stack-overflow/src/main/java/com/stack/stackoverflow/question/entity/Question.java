package com.stack.stackoverflow.question.entity;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.tag.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int answerCount = 0;

    @Column(nullable = false)
    private int questionCount = 0;

    @Column(nullable = false)
    private int vote = 0;

    @Column(nullable = false)
    private int view = 0;

    @ManyToOne
    @JoinColumn(name = "USERPAGE_ID")
    private UserPage userPage;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();
    


    public void setUserPage(UserPage userPage) {
        this.userPage = userPage;
        if(!this.userPage.getQuestions().contains(this)) {
            this.userPage.setQuestions(this);
        }
    }


//    public void setQuestionTags(QuestionTag questionTag) {
//        if(!this.questionTags.contains(questionTag)) this.questionTags.add(questionTag);
//        if (questionTag.getQuestion() != this) {
//            questionTag.setQuestion(this);
//        }
//    }

    public void setAnswers(Answer answer) {
        if(!this.answers.contains(answer)) this.answers.add(answer);
        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }
    }



    public void deleteQuestionTags() {
        this.questionTags.clear();
    }

    public void deleteQuestionTag(QuestionTag questionTag) {
        this.questionTags.remove(questionTag);
    }
}
