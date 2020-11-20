package com.example.demo.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * QQ登录的配置
 * 编写QQ登录的配置
 * @描述
 * @作者 huan
 * @时间 2018年3月3日 - 下午6:05:01
 */
@Configuration
@Component
public class QQLoginConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 拦截 /QQLogin 请求
        QQLoginFilter qqLoginFilter = new QQLoginFilter("/QQLogin", restTemplate());
        qqLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        qqLoginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.authenticationProvider(new QQLoginProvider(restTemplate())).addFilterBefore(qqLoginFilter, AbstractPreAuthenticatedProcessingFilter.class);
        //
    }
}