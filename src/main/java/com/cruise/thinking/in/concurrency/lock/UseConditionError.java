package com.cruise.thinking.in.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 错误使用 {@link Condition} 的方式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseConditionError {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        myService.test();
    }

    static class MyService {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void test() throws InterruptedException {
            System.out.println("start");
            // lock.lock();
            condition.await();
            System.out.println("end");
        }
    }
}
