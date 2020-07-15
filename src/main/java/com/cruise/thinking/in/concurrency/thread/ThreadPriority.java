package com.cruise.thinking.in.concurrency.thread;

/**
 * 线程的优先级
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#setPriority(int)
 * @since 2020/7/15
 */
public class ThreadPriority {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("t1");
        t1.setPriority(Thread.MAX_PRIORITY);
        MyThread t2 = new MyThread();
        t2.setName("t2");
        t2.setPriority(Thread.NORM_PRIORITY);
        MyThread t3 = new MyThread();
        t3.setName("t3");
        t3.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyThread extends Thread {

        int i = 100;

        @Override
        public void run() {
            while (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName()+ " , " + i);
            }
            System.out.println(Thread.currentThread().getName()+ " , end ");
        }
    }
}
