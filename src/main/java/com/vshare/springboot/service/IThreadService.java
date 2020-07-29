package com.vshare.springboot.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public interface IThreadService {
    void log1();
    void log2(CountDownLatch countDownLatch);
    void log3(CyclicBarrier countDownLatch);
    void log4(CyclicBarrier countDownLatch);
}
