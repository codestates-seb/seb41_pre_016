package com.stack.stackoverflow.user.service;

import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){

        verifyExistsEmail(user.getEmail());

        return userRepository.save(user);
    }

    public User updateUser(User user){
        User findUser = findVerifiedUser(user.getUserId());

        Optional.ofNullable(user.getName())
                .ifPresent(name -> findUser.setName(name));
        Optional.ofNullable(user.getPassword())
                .ifPresent(password -> findUser.setPassword(password));

        findUser.setModifiedAt(LocalDateTime.now());

        return userRepository.save(findUser);
    }

    public User findUser(Long userId){
        return findVerifiedUser(userId);
    }

    public void deleteUser(Long userId){
        User findUser = findVerifiedUser(userId);

        userRepository.delete(findUser);
    }

    public User findVerifiedUser(Long userId){
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

        if(user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);

    }
}
