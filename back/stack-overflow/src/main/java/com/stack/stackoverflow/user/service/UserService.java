package com.stack.stackoverflow.user.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.repository.UserPageRepository;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.question.repository.QuestionRepository;
import com.stack.stackoverflow.user.dto.UserRequestDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        verifyExistsEmail(user.getEmail());
        user.setUserPage(new UserPage());

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User findUser = findVerifiedUser(user.getUserId());

        Optional.ofNullable(user.getName())
                .ifPresent(name -> findUser.setName(name));
        Optional.ofNullable(user.getEmail())
                .ifPresent(email -> findUser.setEmail(email));
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(password));

        return userRepository.save(findUser);
    }

    public User findUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        return findVerifiedUser(userId);
    }

    public Page<User> findUsers(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size
        , Sort.by("userId").descending()));
    }

    public void deleteUser(Long userId) {
        User findUser = findVerifiedUser(userId);

        userRepository.delete(findUser);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser =
                userRepository.findById(userId);

        User findUser =
                optionalUser.orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
                });

        return findUser;
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);

    }

    // 로그인
    public User login(User user) {
        // 이메일 검증
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        User findUser =
                optionalUser.orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
                });

        // 비밀번호 검증
        if (!user.getPassword().equals(findUser.getPassword()))
            throw new BusinessLogicException(ExceptionCode.LOGIN_FAIL);

        return findUser;
    }
}