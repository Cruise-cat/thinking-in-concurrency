package com.cruise.thinking.in.concurrency.threadsafe;

import java.time.Instant;

/**
 * synchronized 锁定 Class 对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseSynchronizedBlockClassObject {

    public static void main(String[] args) {
        MyService myServiceA = new MyService();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                myServiceA.test("a");
            }
        };
        MyService myServiceB = new MyService();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                myServiceB.test("b");
            }
        };
        t1.start();
        t2.start();
    }

    static public class MyService {

        public void test(String value) {
            synchronized (MyService.class) {
                System.out.println(value + ":" + Instant.now().toEpochMilli());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(value + ":" + Instant.now().toEpochMilli());
            }

        }
    }
}
