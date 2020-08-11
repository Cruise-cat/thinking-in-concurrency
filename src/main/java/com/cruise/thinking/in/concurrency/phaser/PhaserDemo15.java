package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link Phaser#awaitAdvanceInterruptibly(int, long, TimeUnit)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#awaitAdvanceInterruptibly(int, long, TimeUnit)
 * @since 2020/8/11
 */
public class PhaserDemo15 {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        test3();
    }

    /**
     * 因为5秒之后phaser阶段值并没有发生改变。超时出现异常
     *
     * A begin 1597153969106
     * TimeoutException e
     */
    public static void test1() {
        Phaser phaser = new Phaser(3);
        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();
    }

    /**
     * A begin 1597154032008
     * 1597154035008
     * A   end 1597154035008
     *
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();
        Thread.sleep(1000);
        phaser.arrive();
        Thread.sleep(1000);
        phaser.arrive();
        Thread.sleep(1000);
        phaser.arrive();
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 出现异常的原因是提前将还未到达5秒的线程进行了中断。
     * <p>
     * A begin 1597154088888
     * InterruptedException e
     *
     * @throws InterruptedException
     */
    public static void test3() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();
        Thread.sleep(1000);
        a.interrupt();
    }

    static class ThreadA extends Thread {

        private Phaser phaser;

        public ThreadA(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " begin "
                        + System.currentTimeMillis());
                phaser.awaitAdvanceInterruptibly(0, 5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "   end "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException e");
            } catch (TimeoutException e) {
                System.out.println("TimeoutException e");
            }
        }

    }

}
