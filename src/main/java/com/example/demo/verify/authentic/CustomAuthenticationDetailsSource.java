package com.example.demo.verify.authentic;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomAuthenticationDetailsSource
 * @Description 该接口用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充
 *              该类内容将原本的 WebAuthenticationDetails 替换为了我们的 CustomWebAuthenticationDetails。
 *              然后我们将 CustomAuthenticationDetailsSource 注入Spring Security中，替换掉默认的 AuthenticationDetailsSource
 * @PackageName com.example.demo.verify.authentic.CustomAuthenticationDetailsSource
 * @Author fate
 * @Date 2020/10/18 14:13
 **/
@Component("authenticationDetailsSource")
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }
}
