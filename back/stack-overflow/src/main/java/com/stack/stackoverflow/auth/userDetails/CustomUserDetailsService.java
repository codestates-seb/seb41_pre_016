package com.stack.stackoverflow.auth.userDetails;

import com.stack.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.stack.stackoverflow.exception.BusinessLogicException;
import com.stack.stackoverflow.exception.ExceptionCode;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CustomAuthorityUtils authorityUtils;

    public CustomUserDetailsService(UserRepository userRepository, CustomAuthorityUtils authorityUtils) {
        this.userRepository = userRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalMember = userRepository.findByEmail(username);
        User findUser = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        return new CustomUserDetails(findUser);
    }

    private final class CustomUserDetails extends User implements UserDetails {
        // (1)
        CustomUserDetails(User user) {
            setUserId(user.getUserId());
            setName(user.getName());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRoles(user.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}