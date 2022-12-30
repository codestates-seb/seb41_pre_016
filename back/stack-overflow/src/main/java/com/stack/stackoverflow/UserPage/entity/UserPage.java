package com.stack.stackoverflow.UserPage.entity;

import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.entity.QuestionTag;
import com.stack.stackoverflow.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserPage extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPageId;

    @Column(length = 255)
    private String access;

    @Column(length = 255)
    private String refresh;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userPage", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "userPage", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();

    public void setQuestions(Question question) {
        if(!this.questions.contains(question)) this.questions.add(question);
        if (question.getUserPage() != this) {
            question.setUserPage(this);
        }
    }

    public void setAnswers(Answer answer) {
        if(!this.answers.contains(answer)) this.answers.add(answer);
        if (answer.getUserPage() != this) {
            answer.setUserPage(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if(user.getUserPage() != this) {
            user.setUserPage(this);
        }
    }
}
