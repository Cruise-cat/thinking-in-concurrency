package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 完整的比赛流程
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/26
 */
public class CountDownLatchDemo4 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch comingTag = new CountDownLatch(10);
        CountDownLatch waitTag = new CountDownLatch(1);
        CountDownLatch waitRunTag = new CountDownLatch(10);
        CountDownLatch runTag = new CountDownLatch(1);
        CountDownLatch endTag = new CountDownLatch(10);
        System.out.println("裁判员等待所有运动员达到赛场");
        for (int i = 0; i < 10; i++) {
            Race race = new Race(comingTag, waitTag, waitRunTag, runTag, endTag);
            race.setName(String.valueOf(i));
            race.start();
        }

        comingTag.await();
        System.out.println("所有运动员达到赛场，裁判员巡视赛场");

        TimeUnit.SECONDS.sleep(3);

        System.out.println("各就各位！");
        waitTag.countDown();

        waitRunTag.await();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("发令枪响");
        runTag.countDown();

        endTag.await();

        System.out.println("所有运动员达到终点，比赛结束");

    }

    private static class Race extends Thread {
        private CountDownLatch comingTag;// 裁判等待所有运动员到来
        private CountDownLatch waitTag; // 等待裁判说准备开始
        private CountDownLatch waitRunTag; // 等待起跑
        private CountDownLatch runTag;// 起跑
        private CountDownLatch endTag;// 所有运动员到达终点
        private final Random random = new Random();

        public Race(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch runTag, CountDownLatch endTag) {
            this.comingTag = comingTag;
            this.waitTag = waitTag;
            this.waitRunTag = waitRunTag;
            this.runTag = runTag;
            this.endTag = endTag;
        }

        @Override
        public void run() {
            try {
                System.out.println("运动员" + Thread.currentThread().getName() + "开始出发");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println("运动员" + Thread.currentThread().getName() + "到达赛场");
                comingTag.countDown();
                System.out.println("等待裁判说准备！");
                waitTag.await();
                System.out.println("运动员" + Thread.currentThread().getName() + "准备起跑");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                waitRunTag.countDown();
                runTag.await();
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                endTag.countDown();
                System.out.println("运动员" + Thread.currentThread().getName() + "达到终点");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
