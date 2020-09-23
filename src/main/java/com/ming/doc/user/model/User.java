package com.ming.doc.user.model;

import com.ming.doc.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class User {
    @Setter
    private Long userSeq;
    private String userId;
    private String userName;

    public static User ofEntity(UserEntity entity) {
        return User.builder()
                .userSeq(entity.getUserSeq())
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .build();
    }

    public static User of(Long userSeq, String userId, String userName) {
        return User.builder()
                .userSeq(userSeq)
                .userId(userId)
                .userName(userName)
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private String userId;
        private String userName;

        public static Create of(String userId, String userName) {
            return Create.builder()
                    .userId(userId)
                    .userName(userName)
                    .build();
        }
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class Modify {
        private String userId;
        private String userName;

        public static Modify of(String userId, String userName) {
            return Modify.builder()
                    .userId(userId)
                    .userName(userName)
                    .build();
        }
    }
}
