package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 同步代码块锁定非 this 其他对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseSynchronizedBlockNotThis {

    public static void main(String[] args) {
        MyServiceB myServiceB = new MyServiceB();
        MyServiceA myServiceA = new MyServiceA(myServiceB);

        Thread a = new Thread(() -> {
            myServiceA.method();
        });
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread b = new Thread(() -> {
            // 同一个对象myServiceB和线程a同步
            myServiceB.method();
            // 不是同一个对象myServiceB和线程a异步
            //new MyServiceB().method();
        });
        b.start();
    }

    public static class MyServiceA {

        private MyServiceB myServiceB;

        public MyServiceA(MyServiceB myServiceB) {
            this.myServiceB = myServiceB;
        }

        public void method() {
            synchronized (myServiceB) {
                System.out.println("myServiceA begin " + System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("myServiceA end " + System.currentTimeMillis());
            }
        }
    }

    public static class MyServiceB {
        public synchronized void method() {
            System.out.println("myServiceB begin " + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("myServiceB end " + System.currentTimeMillis());
        }
    }
}
