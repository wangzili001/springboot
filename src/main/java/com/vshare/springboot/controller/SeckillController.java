package com.vshare.springboot.controller;

import com.vshare.springboot.entity.Payment;
import com.vshare.springboot.entity.Result;
import com.vshare.springboot.entity.Stock;
import com.vshare.springboot.service.IPaymentService;
import com.vshare.springboot.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 秒杀
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {
    @Resource
    private IStockService iStockService;

    @Autowired
    private IPaymentService iPaymentService;

    public static Object object = new Object();

    private static volatile int i = 0;

    /**
     * 发生超卖
     */
    @GetMapping("/sell")
    @Transactional
    public Result sell() {
        //todo 时间判断 时间开始暴露接口
        //获取库存
        Stock stock = iStockService.getById(1);
        i++;
        if (stock.getStock() > 0) {
            stock.setStock(stock.getStock() - 1);
            //用户每调用一次减一个
            iStockService.updateById(stock);
            Payment payment = new Payment(i, UUID.randomUUID().toString());
            iPaymentService.save(payment);
            return Result.success("恭喜你 抢到了");
        } else {
            System.out.println(i);
            return Result.success("很可惜 抢完了");
        }
    }

    /**
     * 加锁 synchronized  效率低 但是不超卖
     *
     * @return
     */
    @GetMapping("/synchronizedSell")
    @Transactional
    public Result synchronizedSell() {
        //todo 时间判断 时间开始暴露接口
        //获取库存
        synchronized (object) {
            Stock stock = iStockService.getById(1);
            i++;
            if (stock.getStock() > 0) {
                stock.setStock(stock.getStock() - 1);
                //用户每调用一次减一个
                iStockService.updateById(stock);
                Payment payment = new Payment(i, UUID.randomUUID().toString());
                iPaymentService.save(payment);
                return Result.success("恭喜你 抢到了");
            } else {
                System.out.println(i);
                return Result.success("很可惜 抢完了");
            }
        }
    }

    /**
     * 加锁 lock
     *
     * @return
     */
    @GetMapping("/lockSell")
    @Transactional
    public Result lockSell() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            Stock stock = iStockService.getById(1);
            i++;
            if (stock.getStock() > 0) {
                stock.setStock(stock.getStock() - 1);
                //用户每调用一次减一个
                iStockService.updateById(stock);
                Payment payment = new Payment();
                payment.setSerial(UUID.randomUUID().toString());
                iPaymentService.save(payment);
                return Result.success("恭喜你 抢到了");
            } else {
                System.out.println(i);
                return Result.success("很可惜 抢完了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    @GetMapping("/threadSell")
    @Async("taskExecutor")
    public Result threadSell() {
        Stock stock = iStockService.getById(1);
        i++;
        if (stock.getStock() > 0) {
            stock.setStock(stock.getStock() - 1);
            //用户每调用一次减一个
            iStockService.updateById(stock);
            return Result.success("恭喜你 抢到了");
        } else {
            System.out.println(i);
            return Result.success("很可惜 抢完了");
        }
    }
}
