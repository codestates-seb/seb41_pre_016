package com.stack.stackoverflow.comment.entity;

import com.stack.stackoverflow.answer.entity.Answer;
import com.stack.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    private String content;

    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
