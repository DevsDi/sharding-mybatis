package com.dev.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.dev.interceptor.CurrentUserHandlerMethodArgumentResolver;
import com.dev.interceptor.WebInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport  {

    @Autowired
    private CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }
}
