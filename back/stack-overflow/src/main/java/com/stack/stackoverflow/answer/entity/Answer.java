package com.stack.stackoverflow.answer.entity;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
        if(!this.question.getAnswers().contains(this)) {
            this.question.setAnswers(this);
        }
    }
}