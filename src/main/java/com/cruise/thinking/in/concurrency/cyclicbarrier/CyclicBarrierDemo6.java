package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link CyclicBarrier#await(long, TimeUnit)} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#await(long, TimeUnit)
 * @since 2020/8/3
 */
public class CyclicBarrierDemo6 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("结束，" + System.currentTimeMillis());
        });
        MyService myService = new MyService(cyclicBarrier);
        new Thread(() -> myService.testMethod()).start();
        new Thread(() -> myService.testMethod()).start();
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
                    System.out.println("Thread-0 执行了 cyclicBarrier.await(5, TimeUnit.SECONDS); ");
                    cyclicBarrier.await(5, TimeUnit.SECONDS);
                }
                if (Thread.currentThread().getName().equals("Thread-1")) {
                    System.out.println("Thread-1 执行了 cyclicBarrier.await(); ");
                    cyclicBarrier.await();
                }
                System.out.println(Thread.currentThread().getName() + "开始，" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "进入 InterruptedException ");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "进入 BrokenBarrierException ");
            } catch (TimeoutException e) {
                System.out.println(Thread.currentThread().getName() + "进入 TimeoutException ");
            }
        }
    }
}
/**
 * Thread-0准备，1596467211990
 * Thread-0 执行了 cyclicBarrier.await(5, TimeUnit.SECONDS);
 * Thread-1准备，1596467211990
 * Thread-1 执行了 cyclicBarrier.await();
 * Thread-0进入 TimeoutException
 * Thread-1进入 BrokenBarrierException
 */
