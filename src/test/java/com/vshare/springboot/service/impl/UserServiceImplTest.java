package com.vshare.springboot.service.impl;

import com.vshare.springboot.entity.User;
import com.vshare.springboot.entity.UserRole;
import com.vshare.springboot.service.IUserRoleService;
import com.vshare.springboot.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest

class UserServiceImplTest {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserRoleService iUserRoleService;

    @Transactional()
    @Test
    void add() {
        // 增加用户表
        User user = new User();
        user.setName("Java碎碎念");
        user.setPassword("123456");
        iUserService.add(user);
        // 增加用户角色表
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId("200");
        iUserRoleService.add(userRole);
        //抛异常
        throw new RuntimeException();
    }
}