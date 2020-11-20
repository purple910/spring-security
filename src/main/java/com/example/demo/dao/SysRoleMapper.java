package com.example.demo.dao;

import com.example.demo.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName SysRoleMapper
 * @Description
 * @PackageName com.example.demo.dao.SysRoleMapper
 * @Author fate
 * @Date 2020/10/13 19:32
 **/
@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);

    @Select("SELECT * FROM sys_role WHERE name = #{name}")
    SysRole selectByName(String name);
}

