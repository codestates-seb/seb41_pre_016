package com.stack.stackoverflow.question.entity;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.tag.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionTag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

//    public void setTag(Tag tag) {
//        this.tag = tag;
//        if(!this.tag.getQuestionTags().contains(this)) {
//            this.tag.setQuestionTags(this);
//        }
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//        if(!this.question.getQuestionTags().contains(this)) {
//            this.question.setQuestionTags(this);
//        }
//    }
}
