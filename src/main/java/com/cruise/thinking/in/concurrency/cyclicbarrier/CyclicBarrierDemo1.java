package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * {@link CyclicBarrier} 初步使用
 *
 *  parties 个数和线程数相同
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#await()
 * @since 2020/7/26
 */
public class CyclicBarrierDemo1 {

    public static void main(String[] args) {
        // 设置 5 个 parties 同行者，必须有 5 个线程调用了此 cyclicBarrier 的 await 方法程序才可以继续执行
        // 否则这些线程互相等待，一直呈阻塞状态
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("所有运动员都到了");
        });

        for (int i = 0; i < 5; i++) {
            MyThread thread = new MyThread(cyclicBarrier);
            thread.setName(String.valueOf(i));
            thread.start();
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
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println("运动员" + Thread.currentThread().getName() + "到了");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * 运动员2到了
 * 运动员0到了
 * 运动员3到了
 * 运动员4到了
 * 运动员1到了
 * 所有运动员都到了
 */
