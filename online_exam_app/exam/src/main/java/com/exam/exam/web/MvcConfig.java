package com.exam.exam.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(@SuppressWarnings("null") ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/exam").setViewName("exam");
        registry.addViewController("/result").setViewName("result");
    }
}
