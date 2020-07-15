package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * synchronized 不具备继承性
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class SynchronizedInherit {

    public static void main(String[] args) {
        Child child = new Child();
        Thread t1 = new Thread(() -> child.test());
        Thread t2 = new Thread(() -> child.test());
        t1.start();
        t2.start();
    }

    public static class Parent {

        public synchronized void test(){
            System.out.println("test");
        }
    }

    public static class Child extends Parent {
        /**
         * 继承父类的同步方法时去掉 synchronized 关键字变成了非同步方法
         */
        @Override
        public void test() {
            System.out.println(Thread.currentThread().getName()+" start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" end");
        }
    }
}
