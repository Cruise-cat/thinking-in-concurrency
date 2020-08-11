package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#arriveAndAwaitAdvance()} 示例
 * <p>
 * 调整了 {@link PrintTools#methodB()} 方法代码
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#arriveAndAwaitAdvance()
 * @since 2020/8/7
 */
public class PhaserDemo2 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PrintTools.phaser = phaser;

        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(phaser);
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC(phaser);
        c.setName("C");
        c.start();

    }

    static class PrintTools {
        public static Phaser phaser;

        public static void methodA() {
            System.out.println(Thread.currentThread().getName() + " A1 begin="
                    + System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " A1   end="
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " A2 begin="
                    + System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " A2   end="
                    + System.currentTimeMillis());
        }

        public static void methodB() {
            try {
                System.out.println(Thread.currentThread().getName() + " A1 begin="
                        + System.currentTimeMillis());
                Thread.sleep(5000);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " A1   end="
                        + System.currentTimeMillis());

//                System.out.println(Thread.currentThread().getName() + " A2 begin="
//                        + System.currentTimeMillis());
//                Thread.sleep(5000);
//                phaser.arriveAndAwaitAdvance();
//                System.out.println(Thread.currentThread().getName() + " A2   end="
//                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            PrintTools.methodA();
        }

    }

    static class ThreadB extends Thread {

        private Phaser phaser;

        public ThreadB(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            PrintTools.methodA();
        }

    }

    static class ThreadC extends Thread {

        private Phaser phaser;

        public ThreadC(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            PrintTools.methodB();
        }

    }
}
/**
 * A A1 begin=1596799135417
 * B A1 begin=1596799135417
 * C A1 begin=1596799135418
 * C A1   end=1596799140418
 * B A1   end=1596799140418
 * B A2 begin=1596799140419
 * A A1   end=1596799140419
 * A A2 begin=1596799140419
 */
