package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 多进路、多处理、多出路实验
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/25
 */
public class SemaphoreDemo13 {

    private final Semaphore semaphore = new Semaphore(3);

    public void testMethod() {
        try {
            // 允许最多 3 个线程同时执行
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ",i = " + i);
            }
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo13 semaphoreDemo = new SemaphoreDemo13();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> semaphoreDemo.testMethod()).start();
        }
    }

}

/**
 * Thread-0 start
 * Thread-2 start
 * Thread-1 start
 * Thread-2,i = 0
 * Thread-0,i = 0
 * Thread-2,i = 1
 * Thread-1,i = 0
 * Thread-2,i = 2
 * Thread-0,i = 1
 * Thread-2,i = 3
 * Thread-1,i = 1
 * Thread-2,i = 4
 * Thread-0,i = 2
 * Thread-2 end
 * Thread-1,i = 2
 * Thread-1,i = 3
 * Thread-1,i = 4
 * Thread-1 end
 * Thread-0,i = 3
 * Thread-0,i = 4
 * Thread-0 end
 */

