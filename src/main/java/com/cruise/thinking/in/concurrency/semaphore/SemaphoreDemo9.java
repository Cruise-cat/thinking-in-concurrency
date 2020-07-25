package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 非阻塞方法 {@link Semaphore#tryAcquire()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#tryAcquire()
 * @since 2020/7/25
 */
public class SemaphoreDemo9 {

    private final Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        if (semaphore.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " 获得许可");
            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                new String(i + "");
            }
            semaphore.release();
        } else {
            System.out.println(Thread.currentThread().getName() + " 没有获得许可");
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo9 semaphoreDemo = new SemaphoreDemo9();
        new Thread(() -> semaphoreDemo.testMethod()).start();
        new Thread(() -> semaphoreDemo.testMethod()).start();
    }



}
/**
 * Thread-0 获得许可
 * Thread-1 没有获得许可
 */