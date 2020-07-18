package com.cruise.thinking.in.concurrency.threadstate;

/**
 * 验证线程的 NEW 、RUNNABLE 和 TERMINATED 状态
 *
 * @author Cruise
 * @version 1.0
 * @see Thread.State
 * @since 2020/7/18
 */
public class State1 {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        // NEW
        System.out.println("main 方法中的状态1 " + myThread.getState());
        Thread.sleep(1000);
        myThread.start();
        Thread.sleep(1000);
        // TERMINATED
        System.out.println("main 方法中的状态2 " + myThread.getState());
    }

    private static class MyThread extends Thread {

        public MyThread() {
            // RUNNABLE
            System.out.println("构造方法中的状态 " + Thread.currentThread().getState());
        }

        @Override
        public void run() {
            // RUNNABLE
            System.out.println("run 方法中的状态 " + Thread.currentThread().getState());
        }
    }
}
