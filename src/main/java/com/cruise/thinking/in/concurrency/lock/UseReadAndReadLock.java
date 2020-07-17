package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过 {@link ReentrantReadWriteLock} 实现读读共享
 *
 * @author Cruise
 * @version 1.0
 * @see ReentrantReadWriteLock
 * @since 2020/7/17
 */
public class UseReadAndReadLock {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                myService.read();
            });
            threads[i].start();
        }
    }

    static class MyService {

        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        /**
         * 验证读读共享
         */
        public void read() {
            lock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得读锁");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
