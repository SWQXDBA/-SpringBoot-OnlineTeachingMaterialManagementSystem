package com.example.demo;

import com.example.demo.Interceptions.AdminInterceptor;
import com.example.demo.Interceptions.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        configurer.addPathPrefix("api", c -> true);
    }
    @Bean
    AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/**").excludePathPatterns("/api/register/**");


        registry.addInterceptor(adminInterceptor()).addPathPatterns("/api/Admin/**");
    }
}
