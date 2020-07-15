package com.cruise.thinking.in.concurrency.thread;

/**
 * 守护线程
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#setDaemon(boolean)
 * @since 2020/7/15
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true)
                System.out.println(i++);
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
        // main 线程结束 thread 也随之结束
    }
}
