package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 使用 {@link Thread#setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler)} 处理所有线程中的异常
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseDefaultUncaughtExceptionHandler {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程:" + t.getName() + " 出现了异常");
            }
        });
        MyThread t1 = new MyThread();
        t1.setName("thread t1");
        t1.start();

        MyThread t2 = new MyThread();
        t2.setName("thread t2");
        t2.start();
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            String str = null;
            System.out.println(str.split("."));
        }
    }
}
/**
 * 线程:thread t2 出现了异常：
 * 线程:thread t1 出现了异常：
 */
