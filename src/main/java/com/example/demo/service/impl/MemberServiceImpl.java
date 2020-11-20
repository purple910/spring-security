package com.example.demo.service.impl;

import com.example.demo.entity.MemberEntity;
import com.example.demo.dao.MemberMapper;
import com.example.demo.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fate
 * @since 2020-11-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements IMemberService {

    @Override
    public MemberEntity queryName(String username) {
        return baseMapper.queryName(username);
    }
}
