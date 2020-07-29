package com.vshare.springboot.service;

import com.vshare.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jerry
 * @since 2020-07-02
 */
public interface IUserService extends IService<User> {
    public int add(User user);
}
