package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 正确的使用 {@link Condition} 实现等待/通知机制
 *
 * @author Cruise
 * @version 1.0
 * @see ReentrantLock
 * @see Condition
 * @since 2020/7/17
 */
public class UseCondition {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            while (true) myService.print();
        });
        Thread t2 = new Thread(() -> {
            while (true) myService.show();
        });
        t1.start();
        t2.start();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private boolean flag = false;

        public void print() {
            lock.lock();
            try {
                while (flag) {
                    condition.await();
                }
                System.out.println("★★★★★★★★");
                Thread.sleep(1000);
                flag = true;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void show() {
            lock.lock();
            try {
                while (!flag) {
                    condition.await();
                }
                System.out.println("☆☆☆☆☆☆☆☆");
                Thread.sleep(1000);
                flag = false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
