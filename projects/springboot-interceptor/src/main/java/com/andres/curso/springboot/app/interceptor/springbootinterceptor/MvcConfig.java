package com.andres.curso.springboot.app.interceptor.springbootinterceptor;

import com.andres.curso.springboot.app.interceptor.springbootinterceptor.interceptors.LoadingTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final LoadingTimeInterceptor loadingTimeInterceptor;

    public MvcConfig(LoadingTimeInterceptor loadingTimeInterceptor) {
        this.loadingTimeInterceptor = loadingTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadingTimeInterceptor)
                // .addPathPatterns("/app/bar");
                .addPathPatterns("/app/bar", "/app/foo");
    }
}
