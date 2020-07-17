package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过 {@link ReentrantReadWriteLock} 实现读写互斥
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseReadAndWriteLock {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread read = new Thread(() -> {
            myService.read();
        });
        Thread write = new Thread(() -> {
            myService.write();
        });
        read.start();
        write.start();
    }

    static class MyService {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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
