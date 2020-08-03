package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 方法 {@link CyclicBarrier#reset()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#reset()
 * @since 2020/8/3
 */
public class CyclicBarrierDemo8 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("结束，" + System.currentTimeMillis());
        });
        MyService myService = new MyService(cyclicBarrier);
        new Thread(() -> myService.testMethod()).start();
        new Thread(() -> myService.testMethod()).start();

        Thread.sleep(2000);
        // 重置屏障
        cyclicBarrier.reset();
    }

    static class MyService {
        private CyclicBarrier cyclicBarrier;

        public MyService(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void testMethod() {
            try {
                System.out.println(Thread.currentThread().getName() + "准备，" + System.currentTimeMillis());
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
 *
 * 重置屏障后 2 个等待的线程抛出 BrokenBarrierException 异常
 *
 * Thread-0准备，1596467751835
 * Thread-1准备，1596467751835
 * Thread-0进入 BrokenBarrierException
 * Thread-1进入 BrokenBarrierException
 */
