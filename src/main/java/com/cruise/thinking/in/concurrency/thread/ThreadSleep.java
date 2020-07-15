package com.cruise.thinking.in.concurrency.thread;

/**
 * {@link Thread#sleep(long)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#sleep(long)
 * @see Thread#sleep(long, int)
 * @since 2020/7/15
 */
public class ThreadSleep {

    public static void main(String[] args) {
        System.out.println("main threadName " + Thread.currentThread().getName() + " begin " +
                System.currentTimeMillis());
        // 直接调run方法所以在run方法睡眠的是main线程
        new Thread1().run();
        System.out.println("main threadName " + Thread.currentThread().getName() + " end " +
                System.currentTimeMillis());
        System.out.println("main threadName " + Thread.currentThread().getName() + " begin " +
                System.currentTimeMillis());
        // 调start方法所以睡眠的并不是main线程
        new Thread2().start();
        System.out.println("main threadName " + Thread.currentThread().getName() + " end " +
                System.currentTimeMillis());
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread1 threadName " + Thread.currentThread().getName() + " begin");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 threadName " + Thread.currentThread().getName() + " end");
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread2 threadName " + Thread.currentThread().getName() + " begin " +
                    System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 threadName " + Thread.currentThread().getName() + "end " +
                    System.currentTimeMillis());
        }
    }
}
