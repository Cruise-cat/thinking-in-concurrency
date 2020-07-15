package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 同步代码块锁定 this 对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseSynchronizedBlockThis {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.methodA());
        Thread t2 = new Thread(() -> myService.methodB());
        t1.start();
        t2.start();
    }

    static class MyService {
        public synchronized void methodA() {
            System.out.println("methodA start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("methodA end");
        }

        public void methodB() {
            synchronized (this) {
                System.out.println("methodB start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("methodB end");
            }
        }
    }
}
