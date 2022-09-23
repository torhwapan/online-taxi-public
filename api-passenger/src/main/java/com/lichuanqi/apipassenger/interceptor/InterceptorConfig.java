package com.lichuanqi.apipassenger.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加拦截器到spring容器中
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 具体的拦截器的实现，即为我们本地的：JwtInterceptor
        registry.addInterceptor(new JwtInterceptor())

                // 需要拦截的路径
                .addPathPatterns("/test***")

                // 不需要拦截的路径
                .excludePathPatterns("/test***");

//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
