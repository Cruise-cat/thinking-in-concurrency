package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore#availablePermits()} 方法的作用：
 * 返回此 Semaphore 对象当前可用的许可的数量，此方法通常用来调试，
 * 因为许可数量实时变化并不是固定的。
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#availablePermits()
 * @since 2020/7/25
 */
public class SemaphoreDemo6 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());// 4
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());// 3
        System.out.println(semaphore.availablePermits());// 3
    }

}
