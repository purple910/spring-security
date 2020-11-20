package com.example.demo.dao;

import com.example.demo.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName SysUserRoleMapper
 * @Description
 * @PackageName com.example.demo.dao.SysUserRoleMapper
 * @Author fate
 * @Date 2020/10/13 19:33
 **/
@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
