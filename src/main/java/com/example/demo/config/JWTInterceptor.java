package com.example.demo.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JWTInterceptor
 * @Description
 * @PackageName com.example.demo.config.JWTInterceptor
 * @Author fate
 * @Date 2020/11/21 13:56
 **/
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 获取请求头里的token
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            map.put("msg", "没有设置token");
        }
        else {
            try {
                JWTUtils.verify(token);
                return true;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                map.put("msg", "无效的token");
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                map.put("msg", "token过期");
            } catch (AlgorithmMismatchException e) {
                e.printStackTrace();
                map.put("msg", "token算法不一致");
            }
        }
        map.put("state",false);
        // map --> json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
