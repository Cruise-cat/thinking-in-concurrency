package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 {@link ReentrantLock}
 *
 * @author Cruise
 * @version 1.0
 * @see ReentrantLock
 * @since 2020/7/17
 */
public class UseReentrantLock {

    public static void main(String[] args) throws InterruptedException {
        // 多个线程执行同一个 lock 方法排队执行
        //test1();
        // 不同方法使用相同的 lock 的不同方法需要排队
        test2();
    }

    private static void test1() {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.print());
        Thread t2 = new Thread(() -> myService.print());
        Thread t3 = new Thread(() -> myService.print());
        Thread t4 = new Thread(() -> myService.print());
        Thread t5 = new Thread(() -> myService.print());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static void test2() throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.print());
        Thread t2 = new Thread(() -> myService.show());
        t2.start();
        Thread.sleep(200);
        t1.start();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();

        public void print() {
            // 加锁
            lock.lock();
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " , " + i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁 一般放到 finally 语句块中保证释放锁
                lock.unlock();
            }
        }

        public void show() {
            lock.lock();
            try {
                System.out.println("show run");
                Thread.sleep(2000);
                System.out.println("show end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
