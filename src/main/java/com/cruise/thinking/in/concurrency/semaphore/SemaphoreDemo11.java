package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore#tryAcquire(long, TimeUnit)} 的使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#tryAcquire(long, TimeUnit)
 * @since 2020/7/25
 */
public class SemaphoreDemo11 {

    private final Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            // 2 秒内没有获得许可就返回 false
            if (semaphore.tryAcquire(2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获得许可");
                for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                    new String(i + "");
                }
                semaphore.release();
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有获得许可");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo11 semaphoreDemo = new SemaphoreDemo11();
        new Thread(() -> semaphoreDemo.testMethod()).start();
        new Thread(() -> semaphoreDemo.testMethod()).start();
    }

}
/**
 * Thread-0 获得许可
 * Thread-1 获得许可
 */
