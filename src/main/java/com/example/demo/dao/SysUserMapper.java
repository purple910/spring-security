package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName SysUserMapper
 * @Description
 * @PackageName com.example.demo.dao.SysUserMapper
 * @Author fate
 * @Date 2020/10/13 19:31
 **/
@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);
}
