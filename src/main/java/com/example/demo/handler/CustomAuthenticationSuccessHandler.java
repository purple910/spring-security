package com.example.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomAuthenticationSuccessHandler
 * @Description 用来处理认证成功后逻辑
 *              onAuthenticationSuccess() 方法的第三个参数 Authentication 为认证后该用户的认证信息，这里打印日志后，重定向到了首页。
 * @PackageName com.example.demo.handler.CustomAuthenticationSuccessHandler
 * @Author fate
 * @Date 2020/10/18 19:25
 **/
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功,{}", authentication);

        response.sendRedirect("/");
    }
}

