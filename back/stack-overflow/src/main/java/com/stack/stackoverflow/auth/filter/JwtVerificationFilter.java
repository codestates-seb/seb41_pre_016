package com.stack.stackoverflow.auth.filter;

import com.stack.stackoverflow.auth.jwt.JwtTokenizer;
import com.stack.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import com.stack.stackoverflow.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final UserService userservice;

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, UserService userservice) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.userservice = userservice;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);

        } catch (SignatureException se) {
            request.setAttribute("exception", se);
            System.out.println("!! accessToken의 signiture와 payload가 불일치하면 동작");
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
            System.out.println("!! accesstoken Expired ");
            response.setHeader("error_message", "!! accesstoken Expired ");

            try {
                // 새로운 AccessToken 발생 및 전달
                String accessToken = delegateAccessTokenByRefreshToken(request);
                response.setHeader("Authorization", "Bearer " + accessToken);
            } catch (SignatureException se) {
                request.setAttribute("exception", se);
                System.out.println("!! RefreshToken의 signiture와 payload가 불일치하면 동작");
            } catch (ExpiredJwtException eee) {
                request.setAttribute("exception", eee);
                System.out.println("!! RefreshToken 유효기간 종료");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                System.out.println("!! RefreshToken의 header 불일치하면 동작");
            }

        } catch (Exception e) {
            request.setAttribute("exception", e);
            System.out.println("!! accessToken의 header 불일치하면 동작");
        }

        filterChain.doFilter(request, response);
    }

    private String delegateAccessTokenByRefreshToken(HttpServletRequest request) {
        // Refresh 토큰 유효성 검증
        String jws = request.getHeader("Refresh");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> refreshClaims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        // 새로운 Access 토큰 발행
        User user = userservice.findByEmail((String) refreshClaims.get("sub"));
        String subject = user.getEmail();
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("roles", user.getRoles());

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        // 새로 생성한 access 토큰의 claims 추출 후 context에 저장
        Map<String, Object> newAccessTokenclaims = jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey).getBody();
        setAuthenticationToContext(newAccessTokenclaims);

        return accessToken;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    private void setAuthenticationToContext(Map<String, Object> claims) {
        String email = (String) claims.get("email");
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

// 1. refresh token을 이용해서 access token 새로 발급하기 -> 완료
// 2. context에 저장된 principal과 accesstoken에 저장된 email이 맞는지 확인하는 검증 과정(& 로그인 시 context에 authentication 저장)
// -> 2번 실패

