package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 释放锁示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class ReleaseLock {

    public static void main(String[] args) {
        MyService myService = new MyService();
//        Thread t1 = new Thread(() -> myService.methodA(), "a");
//        Thread t2 = new Thread(() -> myService.methodA(), "b");

        Thread t1 = new Thread(() -> myService.methodB(), "a");
        Thread t2 = new Thread(() -> myService.methodB(), "b");

        t1.start();
        t2.start();
    }

    static class MyService {

        /**
         * 持有锁的线程抛出异常自动释放锁
         */
        public synchronized void methodA() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " start");
            if (Thread.currentThread().getName().equals("a")) {
                throw new NullPointerException();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " end");
        }

        /**
         * 持有锁的线程正常执行完方法自动释放锁
         */
        public synchronized void methodB() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " end");
        }
    }
}
