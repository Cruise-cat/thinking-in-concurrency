package com.cruise.thinking.in.concurrency.threadsafe;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * 多个对象多把锁造成非线程安全
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class MultiLock {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> new MyObject().sub(), "t1");
        Thread t2 = new Thread(() -> new MyObject().sub(), "t2");
        Thread t3 = new Thread(() -> new MyObject().sub(), "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyObject {

        int i = 10;
        // static int i = 10;

        public synchronized void sub() {
            while (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + "e," + i);
            }
        }
    }
}
