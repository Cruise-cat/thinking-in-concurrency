package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * 控制 {@link Phaser} 类的运行时机
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#register()
 * @see Phaser#arriveAndDeregister()
 * @since 2020/8/11
 */
public class PhaserDemo17 {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();
    }

    /**
     * 默认的运行效果
     * <p>
     * Thread-0 A1 begin=1597154615613
     * Thread-2 A1 begin=1597154615613
     * Thread-1 A1 begin=1597154615613
     * Thread-1 A1   end=1597154615613
     * Thread-0 A1   end=1597154615613
     * Thread-2 A1   end=1597154615613
     */
    public static void test1() {
        Phaser phaser = new Phaser(3);
        for (int i = 0; i < 3; i++) {
            ThreadA t = new ThreadA(phaser);
            t.start();
        }
    }

    /**
     * Thread-0 A1 begin=1597154641427
     * Thread-2 A1 begin=1597154641427
     * Thread-1 A1 begin=1597154641427
     * Thread-0 A1   end=1597154646427
     * Thread-1 A1   end=1597154646427
     * Thread-2 A1   end=1597154646427
     *
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        phaser.register();
        for (int i = 0; i < 3; i++) {
            ThreadA t = new ThreadA(phaser);
            t.start();
        }
        Thread.sleep(5000);
        phaser.arriveAndDeregister();
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
}
