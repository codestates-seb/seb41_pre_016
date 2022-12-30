package com.stack.stackoverflow.user.service;

import com.stack.stackoverflow.UserPage.entity.UserPage;
import com.stack.stackoverflow.UserPage.repository.UserPageRepository;
import com.stack.stackoverflow.auth.jwt.JwtTokenizer;
import com.stack.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.helper.event.UserRegistrationApplicationEvent;
import com.stack.stackoverflow.question.repository.QuestionRepository;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPageRepository userPageRepository;

    // sercurity 추가
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final JwtTokenizer jwtTokenizer;

    public UserService(UserRepository userRepository,
                       ApplicationEventPublisher publisher,
                       PasswordEncoder passwordEncoder,
                       CustomAuthorityUtils authorityUtils,
                       JwtTokenizer jwtTokenizer,
                       UserPageRepository userPageRepository) {
        this.userRepository = userRepository;
        this.publisher = publisher;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.jwtTokenizer = jwtTokenizer;
        this.userPageRepository = userPageRepository;
    }

    public User createUser(User user) {
        // 이미 가입된 이메일인지 검증
        verifyExistsEmail(user.getEmail());

        // userPage와 연관관계 매핑
        user.setUserPage(new UserPage());

        // password 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // (4) 추가: DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        publisher.publishEvent(new UserRegistrationApplicationEvent(savedUser));
        return savedUser;
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
        return findVerifiedUser(userId);
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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }

    // 토큰을 이용해서 claims 추출
    public User findclaims(HttpServletRequest request) {
        String refresh = request.getHeader("Refresh");
        Optional<UserPage> OptionalUserPage = userPageRepository.findByRefresh(refresh);

        UserPage findUserPage =
                OptionalUserPage.orElseThrow(() -> {
                    return new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND);
                });

        return findUserPage.getUser();
    }

    // access와 refresh 토큰 저장
    public void saveTokenInUserPage(Long userPageId, String access, String refresh) {
        UserPage userPage = userPageRepository.findById(userPageId).get();
        userPage.setAccess(access);
        userPage.setRefresh(refresh);
        userPageRepository.save(userPage);
    }

    // 로그아웃
    public void logout(HttpServletRequest request) {
        User user = findclaims(request);
        saveTokenInUserPage(user.getUserPage().getUserPageId(), null, null);
    }

    // access와 refresh가 한쌍으로 저장된 경우만 verify 통과
    public boolean matchTokens(String access, String refresh) {
        List<UserPage> userPages = userPageRepository.findAll();
        for(UserPage userPage : userPages) {
            if(userPage.getAccess().equals(access) && userPage.getRefresh().equals(refresh))
                return true;
        }

        return false;
    }
}