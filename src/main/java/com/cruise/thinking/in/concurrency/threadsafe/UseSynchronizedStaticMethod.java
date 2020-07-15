package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * synchronized 关键字使用在静态方法上
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseSynchronizedStaticMethod {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * methodA 锁定的是 MyService.class 所以不管是不是相同的 MyService 实例调用 methodA 都是同步的
     *
     */
    private static void test1(){
        MyService A = new MyService();
        MyService B = new MyService();
        Thread t1 = new Thread(() -> A.methodA());
        Thread t2 = new Thread(() -> B.methodA());
        t1.start();
        t2.start();
    }

    /**
     * 对象锁和 MyService.class 锁不是同一个锁，所以不会互斥
     *
     */
    private static void test2(){
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> MyService.methodA());
        Thread t2 = new Thread(() -> myService.methodB());
        t1.start();
        t2.start();
    }

    static class MyService {


        public static synchronized void methodA() {
            System.out.println("methodA start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("methodA end");
        }

        public synchronized void methodB() {
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
