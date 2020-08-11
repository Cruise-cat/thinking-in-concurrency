package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#awaitAdvanceInterruptibly(int)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#awaitAdvanceInterruptibly(int)
 * @since 2020/8/11
 */
public class PhaserDemo13 {

    public static void main(String[] args) {
        try {
            Phaser phaser = new Phaser(3);
            ThreadA a = new ThreadA(phaser);
            a.setName("A");
            a.start();
            Thread.sleep(5000);
            a.interrupt();
            System.out.println("中断了c");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " A1 begin="
                        + System.currentTimeMillis());
                System.out.println(phaser.getPhase());
                phaser.awaitAdvanceInterruptibly(0);// 符合栏数就wait
                System.out.println(Thread.currentThread().getName() + " A1   end="
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println("进入catch");
            }
        }

    }

}
/**
 * A A1 begin=1597153524804
 * 0
 * 中断了c
 * 进入catch
 */
