package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * {@link CyclicBarrier} 初步使用
 *
 *  线程数 大于 parties 个数
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#await()
 * @since 2020/7/26
 */
public class CyclicBarrierDemo2 {

    public static void main(String[] args) throws InterruptedException {
        // 设置 5 个 parties 同行者，必须有 5 个线程调用了此 cyclicBarrier 的 await 方法程序才可以继续执行
        // 否则这些线程互相等待，一直呈阻塞状态
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("所有运动员都到了");
        });

        for (int i = 0; i < 4; i++) {
            MyThread thread = new MyThread(cyclicBarrier);
            thread.setName(String.valueOf(i));
            thread.start();
            TimeUnit.SECONDS.sleep(2);
        }

    }


    private static class MyThread extends Thread {
        private CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("等待凑齐2个运动员");
                cyclicBarrier.await();
                System.out.println("已经凑齐2个运动员");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * 等待凑齐2个运动员
 * 等待凑齐2个运动员
 * 所有运动员都到了
 * 已经凑齐2个运动员
 * 已经凑齐2个运动员
 * 等待凑齐2个运动员
 * 等待凑齐2个运动员
 * 所有运动员都到了
 * 已经凑齐2个运动员
 * 已经凑齐2个运动员
 */
