package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过 {@link ReentrantReadWriteLock} 实现写写互斥
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseWriteAndWriteLock {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                myService.write();
            });
            threads[i].start();
        }

    }

    static class MyService {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        /**
         * 验证写写互斥
         */
        public void write() {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得写锁");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
