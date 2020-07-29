package com.vshare.springboot.controller;

import com.vshare.springboot.service.IGoodsSaleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(value = "mysql实现锁")
public class MySqlOptimisticLock {
    @Autowired
    private IGoodsSaleService iGoodsSaleService;

    @GetMapping("/mysql/OptimisticLock")
    public void test() throws Exception {
        iGoodsSaleService.addCount("20191017344713049535651840506935",100);
    }
    @GetMapping("/mysql/PCC")
    public void PCC(){
        iGoodsSaleService.pccAddCount("20191017344713049535651840506935",100);
    }
}
