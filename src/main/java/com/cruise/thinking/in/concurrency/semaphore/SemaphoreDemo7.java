package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore#drainPermits()} ()} 方法的作用：
 * 返回可用的许可个数，并将许可个数清零
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#drainPermits()
 * @since 2020/7/25
 */
public class SemaphoreDemo7 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        System.out.println(semaphore.drainPermits());// 4
        System.out.println(semaphore.availablePermits());// 0
        System.out.println(semaphore.availablePermits());// 0
    }

}
