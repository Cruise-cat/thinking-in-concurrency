package com.cruise.thinking.in.concurrency.threadsafe;

import com.cruise.thinking.in.concurrency.annotation.ThreadSafe;

/**
 * 私有变量是线程安全的
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadSafe
public class PrivateVariableThreadSafe {

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

        public void sub() {
            int i = 10;
            while (i > 0) {
                i--;
                System.out.println(Thread.currentThread().getName() + "," + i);
            }
        }
    }
}
