package com.cruise.thinking.in.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 初步使用 {@link CountDownLatch}
 *
 * @author Cruise
 * @version 1.0
 * @see CountDownLatch#await()
 * @see CountDownLatch#countDown()
 * @since 2020/7/26
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        new Thread(() -> myService.testMethod()).start();

        TimeUnit.SECONDS.sleep(2);
        myService.downMethod();
    }

    static class MyService {
        // 创建 1 个计数的 CountDownLatch 对象
        private final CountDownLatch countDownLatch = new CountDownLatch(1);

        public void testMethod() {
            try {
                System.out.println("begin");
                // 执行此代码时 CountDownLatch 的计数是 1，所以当前线程会阻塞
                countDownLatch.await();
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void downMethod() {
            // 执行此方法后 CountDownLatch 的计数变为 0，阻塞的线程将继续执行
            countDownLatch.countDown();
            System.out.println("down");
        }

    }
}
