package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 各就各位准备比赛
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/26
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread(countDownLatch);
            thread.setName(String.valueOf(i));
            thread.start();
        }

        countDownLatch.await();

        System.out.println("都准备好了，开始比赛");

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
                System.out.println("运动员" + Thread.currentThread().getName() + "准备好了");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
