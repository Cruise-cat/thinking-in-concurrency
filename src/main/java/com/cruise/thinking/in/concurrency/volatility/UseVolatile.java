package com.cruise.thinking.in.concurrency.volatility;

/**
 * 使用 volatile 关键字保证变量的可见性
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseVolatile {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        new Thread(() -> myService.test()).start();
        Thread.sleep(2000);
        myService.stop = true;
    }

    static class MyService {

        private volatile boolean stop = false;

        public void test() {
            while (true) {
                if (stop) {
                    System.out.println("stop test");
                    return;
                }
                System.out.println("test");
            }
        }
    }


}
