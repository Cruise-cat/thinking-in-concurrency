package com.cruise.thinking.in.concurrency.art.chapter1;

/**
 * 模拟线程死锁
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/8/8
 */
public class DeadLock {

    private static String lockA = "A";
    private static String lockB = "B";

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (lockA) {
                    Thread.sleep(3000);
                    synchronized (lockB) {
                        System.out.println("t1 end");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lockB) {
                    Thread.sleep(1000);
                    synchronized (lockA) {
                        System.out.println("t2 end");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
