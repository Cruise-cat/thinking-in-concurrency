package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#forceTermination()} 和 {@link Phaser#isTerminated()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#forceTermination()
 * @see Phaser#isTerminated()
 * @since 2020/8/11
 */
public class PhaserDemo16 {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * 控制台出现了2个begin，说明2个线程呈阻塞状态，因为计数未达到3。
     *
     * A A1 begin=1597154331838
     * B A1 begin=1597154331838
     */
    public static void test1() {
        Phaser phaser = new Phaser(3);
        ThreadA a = new ThreadA(phaser);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(phaser);
        b.setName("B");
        b.start();
    }

    /**
     * 类Phaser执行forceTer-mination()方法时仅仅将屏障取消，线程继续执行后面的代码，并不出现异常，
     * 而CyclicBarrier类的reset()方法执行时却出现异常。
     *
     * A A1 begin=1597154371473
     * B A1 begin=1597154371474
     * true
     * B A1   end=1597154372474
     * A A1   end=1597154372474
     */
    public static void test2() {
        try {
            Phaser phaser = new Phaser(3);
            ThreadA a = new ThreadA(phaser);
            a.setName("A");
            a.start();
            ThreadB b = new ThreadB(phaser);
            b.setName("B");
            b.start();
            Thread.sleep(1000);
            phaser.forceTermination();
            System.out.println(phaser.isTerminated());
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

}
