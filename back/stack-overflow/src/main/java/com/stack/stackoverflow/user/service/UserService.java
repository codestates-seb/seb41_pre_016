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
    public Map<String, Object> findclaims(HttpServletRequest request) {
        Map<String, Object> claims = new HashMap<>();
        try {
            // request의 Authorization 검증
            String jws = request.getHeader("Authorization").replace("Bearer ", "");
            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
            claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
        } catch (SignatureException se) {
            request.setAttribute("exception", se);
            System.out.println("!! accessToken의 signiture와 payload가 불일치하면 동작");
            claims.put("access-token error", "access-token의 signiture와 payload가 불일치");
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
            System.out.println("!! accesstoken Expired ");
            claims.put("access-token error", "access-token의 유효기간 만료");
            System.out.println("!! claims : " + claims);

            // access-token 유효기간 만료 시 refresh-token을 들고와서 진행
            try {
                String jws = request.getHeader("Refresh");
                String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
                claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
            } catch (SignatureException se) {
                request.setAttribute("exception", se);
                System.out.println("!! accessToken의 signiture와 payload가 불일치하면 동작");
                claims.put("refresh-token error", "refresh-token의 signiture와 payload가 불일치");
            } catch (ExpiredJwtException eee) {
                request.setAttribute("exception", eee);
                System.out.println("!! accesstoken Expired ");
                claims.put("refresh-token error", "refresh-token의 유효기간 만료");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                System.out.println("!! accessToken의 header 불일치하면 동작");
                claims.put("refresh-token error", "refresh-token의 header 불일치");
            }

        } catch (Exception e) {
            request.setAttribute("exception", e);
            System.out.println("!! accessToken의 header 불일치하면 동작");
            claims.put("access-token error", "access-token의 header 불일치");
        }

        return claims;
    }

    public void saveTokenInUserPage(Long userPageId, String access, String refresh) {
        UserPage userPage = userPageRepository.findById(userPageId).get();
        userPage.setAccess(access);
        userPage.setRefresh(refresh);
        userPageRepository.save(userPage);
    }

    public boolean matchTokens(String access, String refresh) {
        List<UserPage> userPages = userPageRepository.findAll();
        for(UserPage userPage : userPages) {
            if(userPage.getAccess().equals(access) && userPage.getRefresh().equals(refresh))
                return true;
        }

        return false;
    }
}