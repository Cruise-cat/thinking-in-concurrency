package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 使用字符串做锁
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseStringAsLock {

    /**
     * 使用字符串做锁要特别注意字符串的常量池特性
     *
     * @param args
     */
    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.test("AA"));
        t1.setName("A");
        t1.start();
        // 这样会互斥
        //Thread t2 = new Thread(() -> myService.test("AA"));
        // 这样就不互斥了
        Thread t2 = new Thread(() -> myService.test(new String("AA")));
        t2.setName("B");
        t2.start();
    }

    static class MyService {

        public void test(String value) {
            try {
                synchronized (value) {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {

            }

        }

    }
}
