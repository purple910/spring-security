package com.example.demo.dao;

import com.example.demo.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fate
 * @since 2020-11-19
 */
public interface MemberMapper extends BaseMapper<MemberEntity> {

    public MemberEntity queryName(String username);
}
