package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用 {@link Semaphore} 实现同步性，同一时间只能有一个线程执行
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#Semaphore(int)
 * @see Semaphore#acquire()
 * @see Semaphore#release()
 * @since 2020/7/25
 */
public class SemaphoreDemo1 {
    /**
     * 构造函数中 permits 参数是许可的意思，代表允许同一时间执行
     * {@code semaphore.acquire()} 和 {@code semaphore.release()}
     * 之间代码的线程数量.
     * <i>注意：如果 permits 的数量 > 1,并不能保证线程安全</i>
     *
     */
    private Semaphore semaphore = new Semaphore(2);

    /**
     * {@code semaphore.acquire()} 的作用是使用一个许可，是减法操作
     */
    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " begin");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " end");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo1 semaphoreDemo = new SemaphoreDemo1();
        Thread t1 = new Thread(() -> semaphoreDemo.testMethod());
        Thread t2 = new Thread(() -> semaphoreDemo.testMethod());
        Thread t3 = new Thread(() -> semaphoreDemo.testMethod());
        t1.start();
        t2.start();
        t3.start();
    }

}
