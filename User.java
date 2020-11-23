package com.example.demo.entity;

/**
 * @ClassName User
 * @Description
 * @PackageName com.example.demo.entity.User
 * @Author admin
 * @Date 2020/11/20    11:39
 **/
public class User {
    private String name;
    private String pwd;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
