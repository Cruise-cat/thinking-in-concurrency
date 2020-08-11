package com.cruise.thinking.in.concurrency.art.chapter1;

import java.time.Instant;

/**
 * 多线程一定快吗？
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/8/8
 */
public class MustConcurrencyBeFast {

    private static final int COUNT = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        //concurrency();
        serial();
    }

    /**
     * 并行 两个线程
     */
    public static void concurrency() throws InterruptedException {
        long start = Instant.now().toEpochMilli();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < COUNT; i++) {
                a += 5;
            }
        });
        thread.start();

        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }

        thread.join();
        long end = Instant.now().toEpochMilli();
        System.out.println("concurrency 耗时：" + (end - start) + "ms ");
    }

    /**
     * 串行 一个线程
     */
    public static void serial() {
        long start = Instant.now().toEpochMilli();
        int a = 0;
        for (int i = 0; i < COUNT; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }

        long end = Instant.now().toEpochMilli();
        System.out.println("concurrency 耗时：" + (end - start) + "ms");
    }
}
