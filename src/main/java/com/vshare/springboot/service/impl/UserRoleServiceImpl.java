package com.vshare.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshare.springboot.Mapper.UserRoleMapper;
import com.vshare.springboot.entity.UserRole;
import com.vshare.springboot.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2020-07-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Transactional(propagation = Propagation.SUPPORTS)
    public int add(UserRole userRole) {
        return baseMapper.insert(userRole);
    }
}
