package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用多个 {@link Condition} 实现通知部分线程
 *
 * @author Cruise
 * @version 1.0
 * @see ReentrantLock
 * @see Condition
 * @since 2020/7/17
 */
public class UseMultiCondition {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.print());
        Thread t2 = new Thread(() -> myService.show());
        t1.start();
        t2.start();
        Thread.sleep(2000);
        myService.signalAllA();
        Thread.sleep(2000);
        myService.signalAllB();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();
        // 分为两组 A 和 B
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();

        public void print() {
            lock.lock();
            try {
                for (int i = 0; i < 20; i++) {
                    if (i == 10) {
                        conditionA.await();
                    }
                    System.out.println("print=" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void show() {
            lock.lock();
            try {
                for (int i = 0; i < 20; i++) {
                    if (i == 5) {
                        conditionB.await();
                    }
                    System.out.println("show=" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAllA() {
            System.out.println("唤醒 A");
            lock.lock();
            conditionA.signalAll();
            lock.unlock();
        }

        public void signalAllB() {
            System.out.println("唤醒 B");
            lock.lock();
            conditionB.signalAll();
            lock.unlock();
        }
    }
}
