package com.example.demo.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AlipayController
 * @Description
 * @PackageName com.example.demo.alipay.AlipayController
 * @Author fate
 * @Date 2020/10/19 14:52
 **/
@Controller
public class AlipayController {
    @Autowired
    @Qualifier("alipayService")
    private AlipayService alipayService;

    /**
     * web 订单支付
     */
    @GetMapping("/getPagePay")
    @ResponseBody
    public Map<Object, Object> getPagePay() throws Exception{
        /** 模仿数据库，从后台调数据*/
        String outTradeNo = "19960310621211";
        Integer totalAmount = 1;
        String subject = "苹果28";

        String pay = alipayService.webPagePay(outTradeNo, totalAmount, subject);
        System.out.println(pay);

        Map<Object, Object> pays = new HashMap<>();
        pays.put("pay", pay);

        return pays;
    }

    @GetMapping("/getPagePay1")
    @ResponseBody
    @CrossOrigin("http://localhost:8080")
    public void getPagePay1(HttpServletRequest request, HttpServletResponse response) throws Exception{
        /** 模仿数据库，从后台调数据*/
        String outTradeNo = "19960310621211";
        Integer totalAmount = 1;
        String subject = "苹果28";

        String pay = alipayService.webPagePay(outTradeNo, totalAmount, subject);
        System.out.println(pay);


        ResponseCookie cookie = ResponseCookie.from("myCookie", "myCookieValue") // key & value
                .secure(false)		// 在http下也传输
                .domain("localhost")// 域名
                .path("*")			// path
                .maxAge(Duration.ofHours(1))	// 1个小时候过期
                .sameSite("None")	// 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
                .build();

        // 设置Cookie Header
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

//        response.setHeader();
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(pay);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

}
