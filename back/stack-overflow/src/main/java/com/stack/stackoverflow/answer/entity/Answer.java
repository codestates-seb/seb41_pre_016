package com.stack.stackoverflow.answer.entity;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.audit.Auditable;
import com.stack.stackoverflow.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column
    private String content;

    @Column
    private Integer votes;

    @Column
    private Long answerCount;


    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "USERPAGE_ID")
    private UserPage userPage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();


    public String getContent() {
        return content;
    }

    public Integer getVotes() {
        return votes;
    }
}
