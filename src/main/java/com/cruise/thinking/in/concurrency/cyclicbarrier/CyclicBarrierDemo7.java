package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 方法 {@link CyclicBarrier#getNumberWaiting()} 和 {@link CyclicBarrier#getParties()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#getNumberWaiting()
 * @see CyclicBarrier#getParties()
 * @since 2020/8/3
 */
public class CyclicBarrierDemo7 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("结束，" + System.currentTimeMillis());
        });
        MyService myService = new MyService(cyclicBarrier);
        new Thread(() -> myService.testMethod()).start();
        new Thread(() -> myService.testMethod()).start();
        new Thread(() -> myService.testMethod()).start();

        Thread.sleep(2000);
        System.out.println("屏障对象的 parties 个数：" + cyclicBarrier.getParties());
        System.out.println("在屏障对象处等待的线程个数：" + cyclicBarrier.getNumberWaiting());
    }

    static class MyService {
        private CyclicBarrier cyclicBarrier;

        public MyService(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void testMethod() {
            try {
                System.out.println(Thread.currentThread().getName() + "准备，" + System.currentTimeMillis());
                if (Thread.currentThread().getName().equals("Thread-0")) {
                    Thread.sleep(20000000);
                }
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始，" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "进入 InterruptedException ");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "进入 BrokenBarrierException ");
            }
        }
    }
}
/**
 * Thread-0准备，1596467580869
 * Thread-1准备，1596467580869
 * Thread-2准备，1596467580869
 * 屏障对象的 parties 个数：3
 * 在屏障对象处等待的线程个数：2
 */
