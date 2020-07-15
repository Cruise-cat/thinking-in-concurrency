package com.cruise.thinking.in.concurrency.thread;

/**
 * 单独使用 {@link Thread#interrupt()} 并不能停止线程
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseInterruptAlone {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(200);
        myThread.interrupt();
        System.out.println("end");
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
