package com.example.demo.entity;

import java.io.Serializable;

/**
 * @ClassName SysUser
 * @Description
 * @PackageName com.example.demo.entity.SysUser
 * @Author fate
 * @Date 2020/10/13 19:29
 **/
public class SysUser implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    // 省略getter/setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
