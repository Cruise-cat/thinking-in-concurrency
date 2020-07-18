package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 使用 {@link Thread#setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler)} 处理指定线程中的异常
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseUncaughtExceptionHandler {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("thread t1");
        // 对指定的线程对象设置对应的异常处理器
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程:" + t.getName() + " 出现了异常");
            }
        });
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
 * Exception in thread "thread t2" java.lang.NullPointerException
 * at com.cruise.thinking.in.concurrency.threadgroup.UseUncaughtExceptionHandler$MyThread.run(UseUncaughtExceptionHandler.java:32)
 * 线程:thread t1 出现了异常
 */