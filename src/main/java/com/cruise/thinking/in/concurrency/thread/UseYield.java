package com.cruise.thinking.in.concurrency.thread;

/**
 * 使用 {@link Thread#yield()} 方法
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#yield()
 * @since 2020/7/15
 */
public class UseYield {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 500000; i++) {
                // 注释这行代码会耗时小一些
                // Thread.yield();
                System.out.println(i);
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start));
        });
        thread.start();
    }


}
