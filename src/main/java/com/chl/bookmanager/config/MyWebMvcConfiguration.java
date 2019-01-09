package com.chl.bookmanager.config;

import com.chl.bookmanager.interceptor.AdminInterceptor;
import com.chl.bookmanager.interceptor.FileTypeInterceptor;
import com.chl.bookmanager.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器的配置类
 * @author:Administrator
 * @date:2018/12/20/020
 */
@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurerAdapter {//把拦截器注册进spring容器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/myBorrow");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/myFavourite");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/personal");
        registry.addInterceptor(new FileTypeInterceptor()).addPathPatterns("/book/toAddBook");
    }

}
