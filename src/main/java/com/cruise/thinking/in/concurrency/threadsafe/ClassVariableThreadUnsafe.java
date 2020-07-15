package com.cruise.thinking.in.concurrency.threadsafe;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * 如果多个线程同时对一个类的实例变量访问，包括静态，则有可能出现非线程安全的问题
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class ClassVariableThreadUnsafe {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        Thread t1 = new Thread(() -> myObject.sub(), "t1");
        Thread t2 = new Thread(() -> myObject.sub(), "t2");
        Thread t3 = new Thread(() -> myObject.sub(), "t3");
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyObject {

        int i = 10;
        // static int i = 10;

        public void sub() {
            while (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + "e," + i);
            }
        }
    }
}
