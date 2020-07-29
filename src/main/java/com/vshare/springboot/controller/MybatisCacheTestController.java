package com.vshare.springboot.controller;

import com.vshare.springboot.service.IPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("mybatis 缓存测试")
public class MybatisCacheTestController {
    @Resource
    IPaymentService iPaymentService;

    @GetMapping("/mybatis/CacheTest")
    @Transactional
    @ApiOperation("查询两次数据库")
    public void CacheTest(String sql){
        iPaymentService.getOneById(sql);
        iPaymentService.getOneById(sql);
    }
}
