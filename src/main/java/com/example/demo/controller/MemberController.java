package com.example.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.config.JWTUtils;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MemberController
 * @Description
 * @PackageName com.example.demo.controller.MemberController
 * @Author fate
 * @Date 2020/11/21 8:55
 **/
@RestController
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public String queryById(Integer id){
        MemberEntity entity = memberService.queryById(id);
        log.info(entity.toString());
        return entity.toString();
    }

    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public Map<String,Object> info(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","info");

        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        map.put("username",verify.getClaim("username").toString());
        return map;
    }


    @PostMapping("/login")
    public Map<String,Object> login(MemberEntity memberEntity){
        log.info(memberEntity.toString());
        Map<String,Object> map = new HashMap<>();

        try {
            // 密码判断
            Map<String,String> payload =new HashMap<>();
            payload.put("username",memberEntity.getUsername());
            //生成token
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }




}
