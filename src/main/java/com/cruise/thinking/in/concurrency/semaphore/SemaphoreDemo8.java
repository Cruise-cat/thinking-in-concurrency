package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@link Semaphore#getQueueLength()} 和 {@link Semaphore#hasQueuedThreads()} 方法使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#getQueueLength()
 * @see Semaphore#hasQueuedThreads()
 * @since 2020/7/25
 */
public class SemaphoreDemo8 {

    private final Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquire();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("还有" + semaphore.getQueueLength() + "个线程在等待");
            System.out.println("是否还有线程正在等待信号量？" + semaphore.hasQueuedThreads());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo8 semaphoreDemo = new SemaphoreDemo8();
        Thread t1 = new Thread(() -> semaphoreDemo.testMethod(), "t1");
        Thread t2 = new Thread(() -> semaphoreDemo.testMethod(), "t2");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();

    }
}
/**
 * 还有1个线程在等待
 * 是否还有线程正在等待信号量？true
 * 还有0个线程在等待
 * 是否还有线程正在等待信号量？false
 */