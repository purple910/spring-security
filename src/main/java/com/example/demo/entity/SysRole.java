package com.example.demo.entity;

import java.io.Serializable;

/**
 * @ClassName SysRoleEntity
 * @Description
 * @PackageName com.example.demo.entity.SysRoleEntity
 * @Author fate
 * @Date 2020/10/13 19:30
 **/
public class SysRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

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
}

