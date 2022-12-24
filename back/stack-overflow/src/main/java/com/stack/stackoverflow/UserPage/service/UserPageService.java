package com.stack.stackoverflow.UserPage.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.repository.UserPageRepository;
import com.stack.stackoverflow.User.entity.User;
import com.stack.stackoverflow.User.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserPageService {
    private final UserPageRepository userPageRepository;
    private final UserService userService;

    public UserPageService(UserPageRepository userPageRepository,
                           UserService userService) {
        this.userPageRepository = userPageRepository;
        this.userService = userService;
    }

    public UserPage findUserPage(Long userId) {
        User user = userService.findUser(userId);
        return userPageRepository.findByUser(user);
    }
}
