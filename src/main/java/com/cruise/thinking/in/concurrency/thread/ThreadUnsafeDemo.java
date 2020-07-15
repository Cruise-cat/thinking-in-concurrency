package com.cruise.thinking.in.concurrency.thread;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * 线程不安全的示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class ThreadUnsafeDemo {

    public static void main(String[] args) {

        // 1
        MyThread1 myThread1 = new MyThread1();
        Thread t1 = new Thread(myThread1);
        Thread t2 = new Thread(myThread1);
        Thread t3 = new Thread(myThread1);
        Thread t4 = new Thread(myThread1);
        Thread t5 = new Thread(myThread1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    static class MyThread1 extends Thread {
        int i = 5;

        /**
         * 虽然 println 方法内部是被 synchronized 同步的，但是 i-- 是先操作的然后才会执行 println 方法，所以线程不安全
         */
        @Override
        public void run() {
            System.out.println("i-- =" + (i--) + ",ThreadName:" + Thread.currentThread().getName());
        }
    }
}
