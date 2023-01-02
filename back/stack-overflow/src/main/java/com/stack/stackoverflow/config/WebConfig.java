package com.stack.stackoverflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("http://localhost:8080");
//
//        // CORS 에러
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080",
//                        "http://pre-project-fontend.s3-website.ap-northeast-2.amazonaws.com/",
//                        "http://ec2-13-209-69-139.ap-northeast-2.compute.amazonaws.com:8080/") // 허용할 출처
//                .allowedMethods( HttpMethod.GET.name(),
//                        HttpMethod.POST.name(),
//                        HttpMethod.PUT.name(),
//                        HttpMethod.DELETE.name(),
//                        HttpMethod.PATCH.name()) // 허용할 HTTP Method
//                .allowCredentials(true) // 쿠키 인증 요청 허용
//                .maxAge(3000) // 원하는 시간만큼 pre-flight request를 캐싱
//                .exposedHeaders("authorization");
//    }
//}
// 스프링 서버 전역적으로 CORS 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081",
                        "http://pre-project-fontend.s3-website.ap-northeast-2.amazonaws.com/",
                        "http://ec2-13-209-69-139.ap-northeast-2.compute.amazonaws.com:8080/") // 허용할 출처
                .allowedMethods("GET", "POST") // 허용할 HTTP method
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
}