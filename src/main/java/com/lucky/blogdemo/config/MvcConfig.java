package com.lucky.blogdemo.config;

import com.lucky.blogdemo.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lucky
 * @date 2024/7/17
 * @description 配置拦截器类
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/auth/register",
                "/auth/login",
                "/webjars/**",
                "/doc.html",
                "/v3/**"
        );
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
