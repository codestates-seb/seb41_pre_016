package com.stack.stackoverflow.UserPage.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.repository.UserPageRepository;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPageService {
    private final UserPageRepository userPageRepository;
    private final UserService userService;

    public UserPageService(UserPageRepository userPageRepository,
                           UserService userService) {
        this.userPageRepository = userPageRepository;
        this.userService = userService;
    }

    // 유효한 UserPage인지 검증
    public UserPage findUserPage(Long userId) {
        User user = userService.findUser(userId);
        return userPageRepository.findByUser(user);
    }

    // User 정보 업데이트
    public UserPage updateUserPage(User user) {
        // 유효한 UserPage인지 검증
        UserPage userPage = findUserPage(user.getUserId());

        // 유효한 User인지 검증
        User findUser = userService.findVerifiedUser(user.getUserId());

        // User 정보 업데이트
        Optional.ofNullable(user.getName())
                .ifPresent(name -> findUser.setName(name));
        Optional.ofNullable(user.getEmail())
                .ifPresent(email -> findUser.setEmail(email));
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(password));

        userPage.setUser(findUser);

        return userPageRepository.save(userPage);
    }
}
