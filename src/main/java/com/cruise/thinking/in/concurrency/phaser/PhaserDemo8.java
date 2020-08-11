package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#getArrivedParties()} 和 {@link Phaser#getUnarrivedParties()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#getArrivedParties()
 * @see Phaser#getUnarrivedParties()
 * @since 2020/8/7
 */
public class PhaserDemo8 {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(7);
        MyThread[] myThreadArray = new MyThread[5];
        for (int i = 0; i < myThreadArray.length; i++) {
            myThreadArray[i] = new MyThread(phaser);
            myThreadArray[i].setName("Thread" + (i + 1));
            myThreadArray[i].start();
        }
        Thread.sleep(2000);
        System.out.println("已到达：" + phaser.getArrivedParties());
        System.out.println("未到达：" + phaser.getUnarrivedParties());
    }

    static class MyThread extends Thread {

        private Phaser phaser;

        public MyThread(Phaser phaser) {
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
/**
 * Thread1 A1 begin=1596806341597
 * Thread5 A1 begin=1596806341598
 * Thread2 A1 begin=1596806341598
 * Thread3 A1 begin=1596806341598
 * Thread4 A1 begin=1596806341598
 * 已到达：5
 * 未到达：2
 */
