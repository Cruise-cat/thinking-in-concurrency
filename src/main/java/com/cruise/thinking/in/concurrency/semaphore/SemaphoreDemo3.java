package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 动态添加 permits 许可数量
 * 多次调用 {@link Semaphore#release} 或 {@link Semaphore#release(int)}
 * 方法时，可以动态添加 permits 许可数量
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#release()
 * @see Semaphore#release(int)
 * @since 2020/7/25
 */
public class SemaphoreDemo3 {

    /**
     * 实验说明 {@code new Semaphore(5)} 仅仅是指定许可的初始数量，并不是最终数量
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());// 0
        semaphore.release();
        semaphore.release(2);
        semaphore.release();
        semaphore.release();
        System.out.println(semaphore.availablePermits());// 5
        semaphore.release(5);
        System.out.println(semaphore.availablePermits());// 10

    }

}
