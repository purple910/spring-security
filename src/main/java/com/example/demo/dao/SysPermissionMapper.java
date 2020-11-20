package com.example.demo.dao;

import com.example.demo.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysPermissionMapper
 * @Description
 * @PackageName com.example.demo.mapper.SysPermissionMapper
 * @Author fate
 * @Date 2020/10/13 20:50
 **/
@Mapper
public interface SysPermissionMapper {
    @Select("SELECT * FROM sys_permission WHERE role_id=#{roleId}")
    List<SysPermission> listByRoleId(Integer roleId);
}
