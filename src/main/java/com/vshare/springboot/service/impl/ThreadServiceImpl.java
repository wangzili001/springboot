package com.vshare.springboot.service.impl;

import com.vshare.springboot.service.IThreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@Service
public class ThreadServiceImpl implements IThreadService {

    @Override
    @Async("taskExecutor")
    public void log1() {
        System.out.println(Thread.currentThread().getName()+"===============我要睡3秒了===============");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"====================处理完成====================");
    }

    @Override
    @Async("taskExecutor")
    public void log2(CountDownLatch countDownLatch) {
        System.out.println(Thread.currentThread().getName()+"===============我要睡3秒了===============");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
        System.out.println(Thread.currentThread().getName()+"====================处理完成====================");
    }

    @Override
    @Async("taskExecutor")
    public void log3(CyclicBarrier cyclicBarrier) {
        System.out.println(Thread.currentThread().getName()+"===============我要睡3秒了===============");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"====================处理完成====================");
    }
    @Override
    @Async("taskExecutor")
    public void log4(CyclicBarrier cyclicBarrier) {
        System.out.println(Thread.currentThread().getName()+"===============我要睡5秒了===============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"====================处理完成====================");
    }
}
