package com.stack.stackoverflow.tag.entity;

import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.question.entity.QuestionTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 800)
    private String content;

    @Column
    private int questionCount;

    @OneToMany(mappedBy = "tag", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<QuestionTag> questionTags = new ArrayList<>();

    public void setQuestionTags(QuestionTag questionTag) {
        if(!this.questionTags.contains(questionTag)) this.questionTags.add(questionTag);
        if (questionTag.getTag() != this) {
            questionTag.setTag(this);
        }
    }

}