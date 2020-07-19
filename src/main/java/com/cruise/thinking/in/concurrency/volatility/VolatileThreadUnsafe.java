package com.cruise.thinking.in.concurrency.volatility;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * volatile 不是原子性的
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class VolatileThreadUnsafe {

    private static volatile int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
            Thread.sleep(1000);
            count = 0;
        }
    }

    public static void test() throws InterruptedException {
        VolatileThreadUnsafe unsafe = new VolatileThreadUnsafe();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increase();
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        Thread.sleep(2000);
        System.out.println(unsafe.count);
    }


}
