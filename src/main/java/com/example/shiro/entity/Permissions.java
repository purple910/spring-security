package com.example.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: fate
 * @description: 权限对应实体类
 * @date: 2021/4/2  10:42
 **/
@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
}