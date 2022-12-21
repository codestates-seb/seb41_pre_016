package com.stack.stackoverflow.answer.entity;

import com.stack.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column
    private String answerContent;

    @Column
    private Integer answerVote;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "USERPAGE_ID")
    private UserPage userPage;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

}
