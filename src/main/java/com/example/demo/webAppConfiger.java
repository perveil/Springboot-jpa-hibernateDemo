package com.example.demo;


import com.example.demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;


@Configuration
public class webAppConfiger  extends WebMvcConfigurationSupport {
    @Resource
    private MyInterceptor myInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(myInterceptor).addPathPatterns("/menu/**");
   }
}
