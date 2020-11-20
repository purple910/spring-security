package com.example.demo.logout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomLogoutSuccessHandler
 * @Description 配置退出后处理的逻辑，方便做一些别的操作
 * @PackageName com.example.demo.logout.CustomLogoutSuccessHandler
 * @Author fate
 * @Date 2020/10/18 19:51
 **/
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = null;
        if(authentication.getPrincipal() instanceof String){
            username = (String)authentication.getPrincipal();
        }
        else if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            username = user.getUsername();
        }
        log.info("退出成功，用户名：{}", username);

        // 重定向到登录页
        response.sendRedirect("/login");
    }
}

