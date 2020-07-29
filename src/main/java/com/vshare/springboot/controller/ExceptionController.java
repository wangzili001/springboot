package com.vshare.springboot.controller;

import com.vshare.springboot.config.ControllerAdvice;
import com.vshare.springboot.entity.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "全局异常测试")
@ControllerAdvice
public class ExceptionController {
    /**
     * 抛出运行时异常
     */
    @GetMapping("/throwRuntimeException")
    @ApiOperation("RuntimeException")
    public void throwRuntimeException(){
        throw new RuntimeException();
    }

    /**
     * 抛出运行时异常
     */
    @GetMapping("/throwIllegalArgumentException")
    @ApiOperation("throwIllegalArgumentException")
    public void throwIllegalArgumentException(){
        throw new IllegalArgumentException();
    }

    /**
     * 抛出自定义异常
     */
    @GetMapping("/throwMyException")
    @ApiOperation(value = "throwMyException")
    public void throwMyException(){
        throw new MyException("我是主动抛出来的");
    }
}
