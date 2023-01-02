//package com.stack.stackoverflow.config;
//
//import com.stack.stackoverflow.auth.filter.JwtAuthenticationFilter;
//import com.stack.stackoverflow.auth.filter.JwtVerificationFilter;
//import com.stack.stackoverflow.auth.handler.*;
//import com.stack.stackoverflow.auth.jwt.JwtTokenizer;
//import com.stack.stackoverflow.auth.utils.CustomAuthorityUtils;
//import com.stack.stackoverflow.user.repository.UserRepository;
//import com.stack.stackoverflow.user.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class SecurityConfigurationOauth {
//    private final JwtTokenizer jwtTokenizer;
//    private final CustomAuthorityUtils authorityUtils;
//    private final UserService userService;
//
//    public SecurityConfigurationOauth(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, UserService userService) {
//        this.jwtTokenizer = jwtTokenizer;
//        this.authorityUtils = authorityUtils;
//        this.userService = userService;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .headers().frameOptions().sameOrigin()
//                .and()
//                .csrf().disable()
//                .cors(withDefaults())
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
//                .accessDeniedHandler(new UserAccessDeniedHandler())
//                .and()
//                .apply(new CustomFilterConfigurer())
//                .and()
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers(HttpMethod.POST, "/question/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.PATCH, "/question/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/question/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.POST, "/answer/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.PATCH, "/answer/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/answer/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.GET, "/user-page/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.PATCH, "/user-page/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler(new OAuth2UserSuccessHandler(jwtTokenizer, authorityUtils, userService))
//                );
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity builder) throws Exception {
//            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils, userService);
//
//            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
//        }
//    }
//}
