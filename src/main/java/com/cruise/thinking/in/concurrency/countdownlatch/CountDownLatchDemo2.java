package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 裁判在等全部的运动员
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/26
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        // 模拟 10 个运动员
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread(countDownLatch);
            thread.setName(String.valueOf(i));
            thread.start();
        }
        // 在全部运动员到达赛道前 main 线程将阻塞
        countDownLatch.await();
        System.out.println("运动员都到了");
    }

    private static class MyThread extends Thread {

        private final CountDownLatch countDownLatch;
        private final Random random = new Random();

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println("运动员" + Thread.currentThread().getName() + "达到赛道");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
