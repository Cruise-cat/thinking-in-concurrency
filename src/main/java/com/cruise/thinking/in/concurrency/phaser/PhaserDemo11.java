package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#awaitAdvance(int)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#awaitAdvance(int)
 * @since 2020/8/11
 */
public class PhaserDemo11 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB(phaser);
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC(phaser);
        c.setName("C");
        c.start();

        ThreadD d = new ThreadD(phaser);
        d.setName("D");
        d.start();
    }

    static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " A1 begin="
                    + System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " A1   end="
                    + System.currentTimeMillis());
        }

    }

    static class ThreadB extends Thread {

        private Phaser phaser;

        public ThreadB(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " A1 begin="
                    + System.currentTimeMillis());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " A1   end="
                    + System.currentTimeMillis());
        }

    }

    static class ThreadC extends Thread {

        private Phaser phaser;

        public ThreadC(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " A1 begin="
                        + System.currentTimeMillis());
                Thread.sleep(3000);
                phaser.awaitAdvance(0);// 跨栏的栏数
                System.out.println(Thread.currentThread().getName() + " A1   end="
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadD extends Thread {

        private Phaser phaser;

        public ThreadD(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " A1 begin="
                        + System.currentTimeMillis());
                Thread.sleep(5000);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " A1   end="
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
/**
 * A A1 begin=1597153007505
 * B A1 begin=1597153007506
 * C A1 begin=1597153007508
 * D A1 begin=1597153007508
 * D A1   end=1597153012509
 * C A1   end=1597153012509
 * B A1   end=1597153012509
 * A A1   end=1597153012509
 */
