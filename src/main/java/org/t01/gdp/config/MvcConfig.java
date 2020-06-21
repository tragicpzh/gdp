package org.t01.gdp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/student/login").setViewName("student/login");
        registry.addViewController("/teacher/login").setViewName("teacher/login");
        registry.addViewController("/administrator/login").setViewName("administrator/login");
        registry.addViewController("/forgotPassword").setViewName("forgotPassword");
    }

}