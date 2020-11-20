package com.example.demo.verify.authentic;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomWebAuthenticationDetails
 * @Description 获取用户登录时携带的额外信息
 *              我们将前台 form 表单中的 verifyCode 获取到，并通过 get 方法方便被调用
 * @PackageName com.example.demo.verify.authentic.CustomWebAuthenticationDetails
 * @Author fate
 * @Date 2020/10/18 14:04
 **/
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String verifyCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        // verifyCode为页面中验证码的name
        verifyCode = request.getParameter("verifyCode");
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; VerifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
