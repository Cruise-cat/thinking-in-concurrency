package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * {@link CountDownLatch#await(long, TimeUnit)} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CountDownLatch#await(long, TimeUnit)
 * @since 2020/7/26
 */
public class CountDownLatchDemo5 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(2,TimeUnit.SECONDS);
        System.out.println("自动唤醒了");
    }
}
