package com.cruise.thinking.in.concurrency.thread;

/**
 * 简单的创建线程的方式
 *
 * @author Cruise
 * @version 1.0
 * @see Thread
 * @see Runnable
 * @since 2020/7/14
 */
public class CreateThreadWay {

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName().concat("执行了"));
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName().concat("执行了"));
        }
    }
}
