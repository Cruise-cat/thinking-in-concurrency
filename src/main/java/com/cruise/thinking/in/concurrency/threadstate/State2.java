package com.cruise.thinking.in.concurrency.threadstate;

/**
 * 验证线程的 TIMED_WAITING 状态
 *
 * @author Cruise
 * @version 1.0
 * @see Thread.State
 * @since 2020/7/18
 */
public class State2 {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        // TIMED_WAITING
        System.out.println("main 方法中的状态 " + myThread.getState());
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                // RUNNABLE
                System.out.println("run 方法中的状态 " + Thread.currentThread().getState());
                Thread.sleep(3000);
                // RUNNABLE
                System.out.println("run 方法中的状态 " + Thread.currentThread().getState());
            } catch (InterruptedException e) {

            }
        }

    }
}
