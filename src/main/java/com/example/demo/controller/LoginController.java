package com.example.demo.controller;

import com.example.demo.email.SendMail;
import com.example.demo.sms.SndSMS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description
 * @PackageName com.example.demo.controller.LoginController
 * @Author fate
 * @Date 2020/10/18 13:24
 **/
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
//        return "home.html";
        return "home";
    }

    @RequestMapping("/login")
    public String showLogin() {
//        return "login.html";
        return "login";
    }

    //    @RequestMapping("/admin")
//    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String printAdmin() {
//        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
//    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @RequestMapping("/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }


    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    @RequestMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String invalid() {
        return "Session 已过期，请重新登录";
    }

    // 主动踢出用户
    @GetMapping("/kick")
    @ResponseBody
    public String removeUserSessionByUsername(@RequestParam String username) {
        int count = 0;

        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal.equals(username)) {
                // 参数二：是否包含过期的Session
                List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                if (null != sessionsInfo && sessionsInfo.size() > 0) {
                    for (SessionInformation sessionInformation : sessionsInfo) {
                        sessionInformation.expireNow();
                        count++;
                    }
                }
            }
        }
        return "操作成功，清理session共" + count + "个";
    }

    // 获取用户认证信息
    @GetMapping("/me")
    @ResponseBody
    public Object me() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @GetMapping("/me1")
    @ResponseBody
    public Object me(Authentication authentication) {
        return authentication;
    }

    // 短信验证码登录
    @RequestMapping("/sms/code")
    @ResponseBody
    public void sms(@RequestParam("mobile") String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);
        SndSMS sndSMS = new SndSMS();
        boolean snd = sndSMS.sndMessage(mobile, String.valueOf(code));
        if(snd) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("mobile", mobile);
            map.put("code", code);
            session.setAttribute("smsCode", map);
            logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
        }
        else {
            logger.info("短信验证码发送失败");
        }
    }

    // 方式邮件
    @Autowired
    private SendMail sendMail;

    @RequestMapping("/snd/email")
    @ResponseBody
    public void sndEmail(){
        sendMail.testSendSimpleMail();
    }


    @RequestMapping("/ali")
    public String ali(){
        return "pay";
    }


}

