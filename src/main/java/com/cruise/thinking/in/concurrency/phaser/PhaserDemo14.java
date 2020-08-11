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
public class PhaserDemo14 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();
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
                phaser.awaitAdvanceInterruptibly(10);// 不符合栏数就继续向下执行
                System.out.println(Thread.currentThread().getName() + " A1   end="
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println("进入catch");
            }
        }

    }

}
/**
 * A A1 begin=1597153697090
 * 0
 * A A1   end=1597153697090
 */
