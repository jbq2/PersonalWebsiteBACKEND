package com.personalwebsite.personalwebsite.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("https://jbq2-portfolio.herokuapp.com", "http://localhost:4200")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1);
    }
}
