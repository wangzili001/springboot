package com.vshare.springboot.controller;

import com.vshare.springboot.entity.Result;
import com.vshare.springboot.utils.DistributedLockByCurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * zk分布式锁
 */
@RestController
@RequestMapping("/hello")
public class ZKLockController {
    @Autowired
    private DistributedLockByCurator distributedLockByCurator;

    private final static String PATH = "test";

    @GetMapping("/lock1")
    public Result<Boolean> getLock1() {
        Boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByCurator.releaseDistributedLock(PATH);
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return Result.success(true);
    }

    @GetMapping("/lock2")
    public Result<Boolean> getLock2() {
        Boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByCurator.releaseDistributedLock(PATH);
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return Result.success(flag);
    }
}
