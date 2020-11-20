package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fate
 * @since 2020-11-19
 */
public interface IMemberService extends IService<MemberEntity> {
    public MemberEntity queryName(String username);
}
