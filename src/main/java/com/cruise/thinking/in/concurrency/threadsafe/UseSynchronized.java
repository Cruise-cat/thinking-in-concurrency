package com.cruise.thinking.in.concurrency.threadsafe;

import com.cruise.thinking.in.concurrency.annotation.ThreadSafe;

/**
 * 使用 synchronized 关键字修饰实例方法
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadSafe
public class UseSynchronized {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();
    }

    private static void test1() {
        MyObject myObject = new MyObject();
        Thread t1 = new Thread(() -> myObject.sub(), "t1");
        Thread t2 = new Thread(() -> myObject.sub(), "t2");
        Thread t3 = new Thread(() -> myObject.sub(), "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 非 synchronized 修饰的方法可以异步执行，所有被 synchronized 修饰的方法必须同步
     *
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        MyObject myObject = new MyObject();
        Thread t1 = new Thread(() -> myObject.sub(), "t1");
        Thread t2 = new Thread(() -> myObject.getI(), "t2");
        Thread t3 = new Thread(() -> myObject.unlockMethod(), "t3");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();
    }

    static class MyObject {

        int i = 10;

        public synchronized void sub() {
            while (i > 0) {
                i--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "e," + i);
            }
        }

        public synchronized void getI() {
            System.out.println(i);
        }

        public void unlockMethod() {
            System.out.println("==============");
        }
    }
}
