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

    public static void main(String[] args) {
        MyService[] threads = new MyService[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyService();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

    static class MyService extends Thread {
        private volatile static int count = 0;

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                count++;
            }
            System.out.println(count);
        }
    }

}
