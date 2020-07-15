package com.cruise.thinking.in.concurrency.thread;

/**
 * 停止正在睡眠中的线程
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class StopSleepingThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.interrupt();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("begin sleep");
                Thread.sleep(2000);
                System.out.println("end sleep");
            } catch (InterruptedException e) {
                // 清除停止状态
                System.out.println(this.isInterrupted()); // false
                e.printStackTrace();
            }
        }
    }
}
