package com.pickx3.config;

import com.pickx3.exception.error.LogInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;
    private final String uploadImagePath;

    public WebMvcConfig(@Value("${custom.path.upload-images}") String uploadImagesPath){
        this.uploadImagePath = uploadImagesPath;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // CORS 적용할 URI 패턴
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                //자원을 공유 할 오리진 지정
                .allowedOriginPatterns("*")
                //요청 허용 메서드
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                //요청 허용 헤더
                .allowedHeaders("*")
                //쿠키 허용
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogInterceptor())
//                    .addPathPatterns("/*"); // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
//                    //.excludePathPatterns("/"); // 해당 경로는 인터셉터가 가로채지 않는다.
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/home/ubuntu/images/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}