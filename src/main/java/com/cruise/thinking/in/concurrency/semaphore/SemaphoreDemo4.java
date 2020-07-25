package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 等待进入 acquire() 方法的线程允许被中断的例子：线程可以被成功中断
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/25
 */
public class SemaphoreDemo4 {

    private final Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " begin");
            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                new String(i + "");
            }
            System.out.println(Thread.currentThread().getName() + " end");
            semaphore.release();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 进入 catch");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo4 semaphoreDemo = new SemaphoreDemo4();
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
 * t1 begin
 * t2 进入 catch
 * java.lang.InterruptedException
 * 	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireSharedInterruptibly(AbstractQueuedSynchronizer.java:1302)
 * 	at java.util.concurrent.Semaphore.acquire(Semaphore.java:312)
 * 	at com.cruise.thinking.in.concurrency.semaphore.SemaphoreDemo4.testMethod(SemaphoreDemo4.java:19)
 * 	at com.cruise.thinking.in.concurrency.semaphore.SemaphoreDemo4.lambda$main$1(SemaphoreDemo4.java:35)
 * 	at java.lang.Thread.run(Thread.java:748)
 * t1 end
 */