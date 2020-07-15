package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 运行时修改锁对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class ModifyLock {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();
    }

    /**
     * 由于字符串 value 被修改导致 t2 获取的锁对象是 456
     *
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.methodA());
        Thread t2 = new Thread(() -> myService.methodA());
        t1.start();
        Thread.sleep(50);
        t2.start();
    }

    /**
     * 修改 User 对象的 name 属性不会改变锁
     *
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.methodB());
        Thread t2 = new Thread(() -> myService.methodB());
        t1.start();
        Thread.sleep(50);
        t2.start();
    }

    static class MyService {

        private String value = "123";
        private User user = new User("Cruise", 1);


        public void methodA() {
            synchronized (value) {
                System.out.println(Thread.currentThread().getName() + " start");
                // 修改锁对象
                value = "456";
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }

        }

        public void methodB() {
            synchronized (user) {
                System.out.println(Thread.currentThread().getName() + " start");
                // 修改锁对象
                user.name = "Tom";
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }

        }
    }

    static class User {
        private String name;
        private Integer id;

        public User(String name, Integer id) {
            this.name = name;
            this.id = id;
        }
    }
}
