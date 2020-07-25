package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 等待进入 acquire() 方法的线程不允许被中断的例子：线程不会被中断.
 * {@link Semaphore#acquireUninterruptibly(int) 是重载方法，
 * 其作用是等待许可的情况下不允许被中断，如果成功获得锁，则获得 permits 个许可
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#acquireUninterruptibly()
 * @see Semaphore#acquireUninterruptibly(int)
 * @since 2020/7/25
 */
public class SemaphoreDemo5 {

    private final Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        semaphore.acquireUninterruptibly();
        System.out.println(Thread.currentThread().getName() + " begin time " + System.currentTimeMillis());
        for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
            new String(i + "");
        }
        System.out.println(Thread.currentThread().getName() + " end time " + System.currentTimeMillis());
        semaphore.release();
    }


    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo5 semaphoreDemo = new SemaphoreDemo5();
        Thread t1 = new Thread(() -> semaphoreDemo.testMethod(), "t1");
        Thread t2 = new Thread(() -> semaphoreDemo.testMethod(), "t2");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        // 让 t2 线程进入等待
        t2.start();
        // 中断线程 t2
        t2.interrupt();
    }

}
/**
 * t1 begin time 1595658734408
 * t1 end time 1595658735708
 * t2 begin time 1595658735708
 * t2 end time 1595658736930
 */