package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;


/**
 * @ClassName MemberService
 * @Description
 * @PackageName com.example.demo.service.MemberService
 * @Author fate
 * @Date 2020/11/21 8:56
 **/
@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(MemberService.class);

    public MemberEntity queryById(int id){
        MemberEntity entity = new MemberEntity(1,"root","123456",18, Date.valueOf("1998-12-11"),"男");
        log.info(entity.toString());
        return entity;
    }


}
