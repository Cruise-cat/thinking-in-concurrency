package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#arrive()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#arrive()
 * @since 2020/8/7
 */
public class PhaserDemo10 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        MyService service = new MyService(phaser);

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC(service);
        c.setName("C");
        c.start();

    }


    static class MyService {

        public Phaser phaser;

        public MyService(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void testMethodA() {
            try {
                System.out.println(Thread.currentThread().getName() + " begin A1 "
                        + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(phaser.getArrivedParties());
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "   end A1 "
                        + System.currentTimeMillis());

                System.out.println(Thread.currentThread().getName() + " begin A2 "
                        + System.currentTimeMillis());
                Thread.sleep(3000);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "   end A2 "
                        + System.currentTimeMillis());

                System.out.println(Thread.currentThread().getName() + " begin A3 "
                        + System.currentTimeMillis());
                Thread.sleep(3000);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + "   end A3 "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void testMethodB() {
            System.out.println(Thread.currentThread().getName() + " begin A1 "
                    + System.currentTimeMillis());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + "   end A1 "
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A2 "
                    + System.currentTimeMillis());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + "   end A2 "
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A3 "
                    + System.currentTimeMillis());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + "   end A3 "
                    + System.currentTimeMillis());
        }

    }

    static class ThreadA extends Thread {

        private MyService myService;

        public ThreadA(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.testMethodA();
        }

    }

    static class ThreadB extends Thread {

        private MyService myService;

        public ThreadB(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.testMethodA();
        }

    }

    static class ThreadC extends Thread {

        private MyService myService;

        public ThreadC(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.testMethodB();
        }

    }


}
/**
 *
 * 线程 C 在 parties 计数达到 3 后自动重置成 0，线程 A 和 B 由于达不到 parties 为 3 的情况，所以它俩一直在等待。
 *
 * A begin A1 1596806869785
 * B begin A1 1596806869785
 * C begin A1 1596806869786
 * C   end A1 1596806869786
 * C begin A2 1596806869786
 * C   end A2 1596806869786
 * C begin A3 1596806869786
 * C   end A3 1596806869786
 * 0
 * 0
 */
