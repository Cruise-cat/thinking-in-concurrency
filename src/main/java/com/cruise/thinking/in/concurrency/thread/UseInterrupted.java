package com.cruise.thinking.in.concurrency.thread;

/**
 * 使用 {@link Thread#interrupted()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#interrupted()
 * @since 2020/7/15
 */
public class UseInterrupted {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(200);
        myThread.interrupt();
        // 调用 MyThread.interrupted()代码的是 main 线程，main线程并未中断
        // 如果使用这行代码尝试 第一次是 true 第二次是 false
        // Thread.currentThread().interrupt();
        System.out.println(MyThread.interrupted());// false
        System.out.println(MyThread.interrupted());// false
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
