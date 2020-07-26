package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * {@link CountDownLatch#getCount()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CountDownLatch#getCount()
 * @since 2020/7/26
 */
public class CountDownLatchDemo6 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println(countDownLatch.getCount());// 5
        countDownLatch.countDown();
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());// 3
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());// 0
    }
}
