package com.ming.doc.user.controller;

import com.ming.doc.user.model.User;
import com.ming.doc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userSeq}")
    public ResponseEntity<User> findUser(@PathVariable Long userSeq) {
        User user = userService.findUserByUserSeq(userSeq);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        Long userSeq = userService.createUser(user);
        return ResponseEntity.ok().body(userSeq);
    }

    @PutMapping("/{userSeq}")
    public ResponseEntity<User> modifyUser(@PathVariable Long userSeq, @RequestBody User user) {
        user.setUserSeq(userSeq);
        User modifyUser = userService.modifyUser(user);
        return ResponseEntity.ok().body(modifyUser);
    }

    @DeleteMapping("/{userSeq}")
    public ResponseEntity deleteUser(@PathVariable Long userSeq) {
        userService.deleteUser(userSeq);
        return ResponseEntity.ok().build();
    }
}
