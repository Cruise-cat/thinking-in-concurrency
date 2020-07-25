package com.cruise.thinking.in.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Semaphore 实现多生产者/多消费者模式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/25
 */
public class ProducerAndConsumer {

    static class RepastService {
        private final Semaphore setSemaphore = new Semaphore(10);// 厨师
        private final Semaphore getSemaphore = new Semaphore(20);// 就餐者
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition setCondition = lock.newCondition();
        private final Condition getCondition = lock.newCondition();
        private final Object[] producePosition = new Object[4];// 最多只有 4 个盒子盛放菜品

        public boolean isEmpty() {
            boolean flag = true;
            for (Object o : producePosition) {
                if (o != null) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        public boolean isFull() {
            boolean flag = true;
            for (Object o : producePosition) {
                if (o == null) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        public void set() {
            try {
                // 最多允许 10 个厨师同时做菜
                setSemaphore.acquire();
                lock.lock();
                while (isFull()) {
                    System.out.println("厨师在等待");
                    setCondition.await();
                }
                for (int i = 0; i < producePosition.length; i++) {
                    if (producePosition[i] == null) {
                        producePosition[i] = "菜肴";
                        System.out.println(Thread.currentThread().getName() + " 做了一个菜：" + producePosition[i]);
                        break;
                    }
                }
                getCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
                setSemaphore.release();
            }
        }

        public void get() {
            try {
                // 最多允许 20 个客人同时就餐
                getSemaphore.acquire();
                lock.lock();
                while (isEmpty()) {
                    System.out.println("客人在等待");
                    getCondition.await();
                }
                for (int i = 0; i < producePosition.length; i++) {
                    if (producePosition[i] != null) {
                        System.out.println(Thread.currentThread().getName() + " 吃了一个菜：" + producePosition[i]);
                        producePosition[i] = null;
                        break;
                    }
                }
                setCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
                getSemaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        RepastService repastService = new RepastService();
        Thread[] producers = new Thread[60];
        Thread[] consumers = new Thread[60];
        for (int i = 0; i < 60; i++) {
            producers[i] = new Thread(() -> repastService.set(), "producer - " + i);
            consumers[i] = new Thread(() -> repastService.get(), "consumer - " + i);
        }

        for (int i = 0; i < 60; i++) {
            producers[i].start();
            consumers[i].start();
        }
    }
}
