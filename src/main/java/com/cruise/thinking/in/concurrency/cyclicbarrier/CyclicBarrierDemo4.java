package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 用 {@link CyclicBarrier} 实现阶段跑比赛
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/26
 */
public class CyclicBarrierDemo4 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        MyService myService = new MyService(cyclicBarrier);

        MyThread t1 = new MyThread(myService);
        t1.setName("t1");
        t1.start();
        MyThread t2 = new MyThread(myService);
        t2.setName("t2");
        t2.start();
        MyThread t3 = new MyThread(myService);
        t3.setName("t3");
        t3.start();
        MyThread t4 = new MyThread(myService);
        t4.setName("t4");
        t4.start();
    }

    private static class MyService{
        private CyclicBarrier cyclicBarrier;

        public MyService(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void beginRun() {
            try {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(Thread.currentThread().getName() + "开始第一棒," + (cyclicBarrier.getNumberWaiting() + 1));
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "结束第一棒," + cyclicBarrier.getNumberWaiting());
                TimeUnit.SECONDS.sleep(random.nextInt(10));
                System.out.println(Thread.currentThread().getName() + "开始第二棒," + (cyclicBarrier.getNumberWaiting() + 1));
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "结束第二棒," + cyclicBarrier.getNumberWaiting());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThread extends Thread {
        private MyService myService;

        public MyThread(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.beginRun();
        }
    }
}
