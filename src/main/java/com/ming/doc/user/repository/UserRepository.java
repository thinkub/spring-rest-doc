package com.ming.doc.user.repository;

import com.ming.doc.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
}
