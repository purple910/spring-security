package com.example.demo.service;

import com.example.demo.dao.SysRoleMapper;
import com.example.demo.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysRoleService
 * @Description
 * @PackageName com.example.demo.service.SysRoleService
 * @Author fate
 * @Date 2020/10/13 19:35
 **/
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }

}
