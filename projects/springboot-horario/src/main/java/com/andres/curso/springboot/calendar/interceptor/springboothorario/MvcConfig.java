package com.andres.curso.springboot.calendar.interceptor.springboothorario;

import com.andres.curso.springboot.calendar.interceptor.springboothorario.interceptors.CalendarInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final CalendarInterceptor calendarInterceptor;

    public MvcConfig(CalendarInterceptor calendarInterceptor) {
        this.calendarInterceptor = calendarInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendarInterceptor).addPathPatterns("/foo");
    }
}
