package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore#tryAcquire(int, long, TimeUnit)} 的使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#tryAcquire(int, long, TimeUnit)
 * @since 2020/7/25
 */
public class SemaphoreDemo12 {

    private final Semaphore semaphore = new Semaphore(3);

    public void testMethod() {
        try {
            // 2 秒内没有获得 2 个许可就返回 false
            if (semaphore.tryAcquire(2,2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获得许可");
                for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                    new String(i + "");
                }
                semaphore.release(2);
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有获得许可");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo12 semaphoreDemo = new SemaphoreDemo12();
        new Thread(() -> semaphoreDemo.testMethod()).start();
        new Thread(() -> semaphoreDemo.testMethod()).start();
    }

}
/**
 * Thread-0 获得许可
 * Thread-1 获得许可
 */
