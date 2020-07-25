package com.cruise.thinking.in.concurrency.semaphore;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore#acquire(int)} 的作用
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#acquire(int)
 * @since 2020/7/25
 */
public class SemaphoreDemo2 {

    private final Semaphore semaphore = new Semaphore(10);
    private final Random random = new Random();

    /**
     * 一共初始化了 10 个许可，每调用一次 {@code semaphore.acquire(2)} 都会消耗 2 个许可，
     * 所以同一时刻最多有 5 个线程执行代码
     */
    public void testMethod() {
        try {
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + " begin " + Instant.now().toEpochMilli());
            int seconds = random.nextInt(3);
            if (seconds <= 2) {
                seconds += 3;
            }
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println(Thread.currentThread().getName() + " 停止了 " + seconds + " 秒");
            System.out.println(Thread.currentThread().getName() + " end " + Instant.now().toEpochMilli());
            semaphore.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo2 semaphoreDemo = new SemaphoreDemo2();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> semaphoreDemo.testMethod()).start();
        }

    }

}
