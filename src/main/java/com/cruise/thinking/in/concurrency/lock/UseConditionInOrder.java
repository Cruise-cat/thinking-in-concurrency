package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 {@link Condition} 实现顺序执行
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseConditionInOrder {

    private volatile static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread a = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    // 必须用while判断
                    while (nextPrintWho != 1) {
                        conditionA.await();
                    }
                    nextPrintWho = 2;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Thread A " + i);
                    }
                    conditionB.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }
                    nextPrintWho = 3;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Thread B " + i);
                    }
                    conditionC.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        };
        Thread c = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 3) {
                        conditionC.await();
                    }
                    nextPrintWho = 1;
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Thread C " + i);
                    }
                    conditionA.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        };

        Thread[] aArray = new Thread[25];
        Thread[] bArray = new Thread[25];
        Thread[] cArray = new Thread[25];
        for (int i = 0; i < aArray.length; i++) {
            aArray[i] = new Thread(a);
            bArray[i] = new Thread(b);
            cArray[i] = new Thread(c);
            aArray[i].start();
            bArray[i].start();
            cArray[i].start();
        }
    }
}
