package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 验证屏障重置性以及 {@link CyclicBarrier#getNumberWaiting()} 方法的使用
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#getNumberWaiting()
 * @since 2020/7/26
 */
public class CyclicBarrierDemo3 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        MyThread t1 = new MyThread(cyclicBarrier);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());// 1

        MyThread t2 = new MyThread(cyclicBarrier);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());// 2

        MyThread t3 = new MyThread(cyclicBarrier);
        t3.start();
        TimeUnit.MILLISECONDS.sleep(500);
        // CyclicBarrier 具有屏障重置性，也就是 parties 可以重置为 0
        System.out.println(cyclicBarrier.getNumberWaiting());// 0

        MyThread t4 = new MyThread(cyclicBarrier);
        t4.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());// 1
    }

    private static class MyThread extends Thread {
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
