package com.example.demo.service;

import com.example.demo.dao.SysUserRoleMapper;
import com.example.demo.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserRoleService
 * @Description
 * @PackageName com.example.demo.service.SysUserRoleService
 * @Author fate
 * @Date 2020/10/13 19:34
 **/
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}

