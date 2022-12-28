package com.stack.stackoverflow.user.entity;

<<<<<<< HEAD
import com.stack.stackoverflow.UserPage.entity.UserPage;
=======
>>>>>>> origin/dev-leeho
import com.stack.stackoverflow.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> origin/dev-leeho

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

<<<<<<< HEAD
    @Column(length = 50, nullable = false,updatable = false, unique = true)
=======
    @Column(length = 50, nullable = false, updatable = false, unique = true)
>>>>>>> origin/dev-leeho
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column
    private int questionCount;

    @Column
    private int answerCount;

<<<<<<< HEAD
    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserPage userPage;


    public User(String userName){
        this.name = name;
    }

    public void setUserPage(UserPage userPage) {
        this.userPage = userPage;
        if(userPage.getUser() != this) {
            userPage.setUser(this);
        }
    }

=======
    public User(String name){
        this.name = name;
    }

>>>>>>> origin/dev-leeho
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/dev-leeho
