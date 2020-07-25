package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 公平信号量与非公平信号量
 * <p>非公平信号量运行的效果是线程启动的顺序与调用 {@link Semaphore#acquire()} 的顺序无关，也就是说先启动的线程不一定先获得许可</p>
 * <p>公平信号量运行的效果是线程启动的顺序与调用 {@link Semaphore#acquire()} 的顺序有关，也就是说先启动的线程优先获得许可</p>
 *
 * @author Cruise
 * @version 1.0
 * @see Semaphore#Semaphore(int, boolean)
 * @since 2020/7/25
 */
public class SemaphoreFairDemo {

    public static void main(String[] args) {
        // 使用非公平信号量
        Service service = new Service(1, false);
        // 使用公平信号量
        //Service service = new Service(1, true);
        new MyThread(service).start();

        for (int i = 0; i < 4; i++) {
            new MyThread(service).start();
        }
    }

    static class Service {
        private final Semaphore semaphore;

        public Service(int permits, boolean fair) {
            this.semaphore = new Semaphore(permits, fair);
        }

        public void testMethod() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "执行中");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    static class MyThread extends Thread {
        private Service service;

        public MyThread(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "启动了!");
            service.testMethod();
        }
    }

}
/**
 * 非公平信号量
 *
 * Thread-2启动了!
 * Thread-1启动了!
 * Thread-4启动了!
 * Thread-0启动了!
 * Thread-3启动了!
 * Thread-1执行中
 * Thread-0执行中
 * Thread-4执行中
 * Thread-2执行中
 * Thread-3执行中
 */
/**
 * 公平信号量
 *
 * Thread-1启动了!
 * Thread-0启动了!
 * Thread-1执行中
 * Thread-3启动了!
 * Thread-4启动了!
 * Thread-2启动了!
 * Thread-0执行中
 * Thread-3执行中
 * Thread-4执行中
 * Thread-2执行中
 */
