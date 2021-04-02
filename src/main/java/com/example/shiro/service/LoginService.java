package com.example.shiro.service;

import com.example.shiro.entity.User;

/**
 * @author: administer
 * @description:
 * @date: 2021/4/2  10:50
 **/
public interface LoginService {

    /**
     *
     * @param getMapByName
     * @return
     */
    public User getUserByName(String getMapByName);
}
