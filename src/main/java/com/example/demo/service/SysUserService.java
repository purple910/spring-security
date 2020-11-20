package com.example.demo.service;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserService
 * @Description
 * @PackageName com.example.demo.service.SysUserService
 * @Author fate
 * @Date 2020/10/13 19:33
 **/
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}

