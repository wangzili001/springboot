package com.vshare.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshare.springboot.Mapper.UserMapper;
import com.vshare.springboot.entity.User;
import com.vshare.springboot.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Transactional(propagation = Propagation.MANDATORY)
    public int add(User user){
        return baseMapper.insert(user);
    }
}
