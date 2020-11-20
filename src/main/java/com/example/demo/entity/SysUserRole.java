package com.example.demo.entity;

import java.io.Serializable;

/**
 * @ClassName SysUserRole
 * @Description
 * @PackageName com.example.demo.entity.SysUserRole
 * @Author fate
 * @Date 2020/10/13 19:31
 **/
public class SysUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;

    // 省略getter/setter

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
