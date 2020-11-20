package com.example.demo.service;

import com.example.demo.dao.SysPermissionMapper;
import com.example.demo.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysPermissionService
 * @Description
 * @PackageName com.example.demo.service.SysPermissionService
 * @Author fate
 * @Date 2020/10/13 20:52
 **/
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     */
    public List<SysPermission> listByRoleId(Integer roleId) {
        return permissionMapper.listByRoleId(roleId);
    }
}
