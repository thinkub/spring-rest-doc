package com.ming.doc.user.service;

import com.ming.doc.user.entity.UserEntity;
import com.ming.doc.user.model.User;
import com.ming.doc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long createUser(User user) {
        UserEntity userEntity = UserEntity.createUser(user.getUserId(), user.getUserName());
        return userRepository.save(userEntity).getUserSeq();
    }

    @Transactional
    public User modifyUser(User user) {
        Optional<UserEntity> userOptional = userRepository.findById(user.getUserSeq());
        if (userOptional.isPresent()) {
            return user;
        }
        UserEntity userEntity = userOptional.get();
        userEntity.modifyUser(user);
        return User.ofEntity(userEntity);
    }

    @Transactional
    public void deleteUser(Long userSeq) {
        Optional<UserEntity> userEntity = userRepository.findById(userSeq);
        userEntity.ifPresent(userRepository::delete);
    }

    public User findUserByUserSeq(Long userSeq) {
        Optional<UserEntity> userEntity = userRepository.findById(userSeq);
        return userEntity.map(User::ofEntity).orElse(null);
    }
}
