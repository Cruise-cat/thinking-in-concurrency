package com.cruise.thinking.in.concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Semaphore 创建字符串池
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/25
 */
public class ListPool {

    private int maxPoolSize = 3;
    private int permits = 5;
    private List<String> list = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(permits);
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public ListPool() {
        for (int i = 0; i < maxPoolSize; i++) {
            list.add("i = " + i);
        }
    }

    public String get() {
        String value = null;
        try {
            semaphore.acquire();
            lock.lock();
            while (list.size() == 0) {
                condition.await();
            }
            value = list.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public void put(String value) {
        lock.lock();
        list.add(value);
        condition.signalAll();
        lock.unlock();
        semaphore.release();
    }

    private static class MyThread extends Thread {
        private ListPool listPool;

        public MyThread(ListPool listPool) {
            this.listPool = listPool;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String value = listPool.get();
                System.out.println(Thread.currentThread().getName() + " 取得值 " + value);
                listPool.put(value);
            }
        }
    }

    public static void main(String[] args) {
        ListPool listPool = new ListPool();
        for (int i = 0; i < 5; i++) {
            new MyThread(listPool).start();
        }
    }

}


