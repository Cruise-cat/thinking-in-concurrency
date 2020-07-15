package com.cruise.thinking.in.concurrency.thread;

/**
 * {@link Thread#isInterrupted()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#isInterrupted()
 * @since 2020/7/15
 */
public class UseIsInterrupted {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(200);
        myThread.interrupt();
        System.out.println(myThread.isInterrupted());// true
        System.out.println(myThread.isInterrupted());// true
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                System.out.println(i);
            }
        }
    }
}
