package com.stack.stackoverflow.user.entity;

import com.stack.stackoverflow.UserPage.entity.UserPage;

import com.stack.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USERS")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column
    private int questionCount;

    @Column
    private int answerCount;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserPage userPage;

    public void setUserPage(UserPage userPage) {
        this.userPage = userPage;
        if (userPage.getUser() != this) {
            userPage.setUser(this);
        }
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}