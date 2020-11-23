package com.example.demo.controller;


import com.example.demo.entity.MemberEntity;
import com.example.demo.service.IMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fate
 * @since 2020-11-19
 */
@RestController
public class MemberController {
    private Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    IMemberService memberService;

    @GetMapping("/query")
    public String query(@RequestParam("username")String username){
        MemberEntity member = memberService.queryName(username);
        logger.info("member = {}", member);
        return "ok";
    }

    @PostMapping("/info")
    public String info(){
        return "info";
    }




    @GetMapping("/aa")
    public String aa(){
        return "aaa";
    }


}
