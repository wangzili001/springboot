package com.vshare.springboot.controller;

import com.vshare.springboot.service.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@RestController
@Api("测试springboot 多线程")
public class ThreadController {
    @Resource
    IThreadService iThreadService;

    @GetMapping("startMethod1")
    @ApiOperation("测试多线程方法调用 不等待线程执行完成直接返回")
    public String startMethod1(){
        iThreadService.log1();
        iThreadService.log1();
        iThreadService.log1();
        return "完成3次";
    }
    @GetMapping("startMethod2")
    @ApiOperation("测试多线程方法调用 CountDownLatch等待线程执行完成直接返回")
    public String startMethod2(){
        CountDownLatch countDownLatch = new CountDownLatch(3);
        iThreadService.log2(countDownLatch);
        iThreadService.log2(countDownLatch);
        iThreadService.log2(countDownLatch);
        try {
            countDownLatch.await();
        }catch (Exception e){
            System.out.println("异常了");
        }finally {
            return "完成3次";
        }
    }

    @GetMapping("startMethod3")
    @ApiOperation("测试多线程方法调用 CyclicBarrier等待线程执行完成直接返回")
    public String startMethod3(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> {
            System.out.println("召唤神龙");
        });
        iThreadService.log3(cyclicBarrier);
        iThreadService.log4(cyclicBarrier);
        iThreadService.log3(cyclicBarrier);
        try {
            cyclicBarrier.await();
        }catch (Exception e){
            System.out.println("异常了");
        }finally {
            return "完成3次";
        }
    }
}
