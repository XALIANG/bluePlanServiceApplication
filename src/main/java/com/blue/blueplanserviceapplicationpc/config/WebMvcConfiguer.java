package com.blue.blueplanserviceapplicationpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns("/user/public/**"); //访问以/admin/为前缀的所有路径都需要对token进行验证
    }


    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }
}