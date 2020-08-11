package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#getPhase()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#getPhase()
 * @since 2020/8/7
 */
public class PhaserDemo4 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        ThreadA a = new ThreadA(phaser);
        a.start();
    }

    static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void run() {
            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

            System.out.println("A  begin");
            phaser.arriveAndAwaitAdvance();
            System.out.println("A    end phase value=" + phaser.getPhase());

        }

    }

}
/**
 * A  begin
 * A    end phase value=1
 * A  begin
 * A    end phase value=2
 * A  begin
 * A    end phase value=3
 * A  begin
 * A    end phase value=4
 */
