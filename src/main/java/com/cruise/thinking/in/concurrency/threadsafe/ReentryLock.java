package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 锁重入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class ReentryLock {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread a = new Thread(() -> {
            myService.methodA();
        });
        Thread b = new Thread(() -> {
            myService.methodA();
        });
        a.start();
        b.start();
    }

    static public class MyService {
        public synchronized void methodA() {
            System.out.println("a begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 调B方法
            methodB();
            System.out.println("a end");
        }

        public synchronized void methodB() {
            System.out.println("b begin");
            System.out.println("b end");
        }
    }
}
