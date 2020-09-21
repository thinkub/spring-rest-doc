package com.ming.doc.user.entity;

import com.ming.doc.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSeq;

    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 30)
    private String userName;

    public static UserEntity createUser(String userId, String userName) {
        return new UserEntity(userId, userName);
    }

    public void modifyUser(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }

    private UserEntity(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
