package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore#tryAcquire(int)} 的使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#tryAcquire(int)
 * @since 2020/7/25
 */
public class SemaphoreDemo10 {

    private final Semaphore semaphore = new Semaphore(3);

    public void testMethod() {
        // 一次性获取 2 个许可
        if (semaphore.tryAcquire(2)) {
            System.out.println(Thread.currentThread().getName() + " 获得许可");
            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                new String(i + "");
            }
            // 注意释放的数量
            semaphore.release(2);
        } else {
            System.out.println(Thread.currentThread().getName() + " 没有获得许可");
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo10 semaphoreDemo = new SemaphoreDemo10();
        new Thread(() -> semaphoreDemo.testMethod()).start();
        new Thread(() -> semaphoreDemo.testMethod()).start();
    }

}
/**
 * Thread-0 获得许可
 * Thread-1 没有获得许可
 */
