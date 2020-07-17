package com.cruise.thinking.in.concurrency.lock;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 {@link ReentrantLock} 的 API
 *
 * @author Cruise
 * @version 1.0
 * @see ReentrantLock
 * @since 2020/7/17
 */
public class UseLockApi {

    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        //test9();
        //test10();
        test11();
    }

    /**
     * @see Condition#awaitUntil(Date) 
     */
    private static void test11() {
        MyService myService = new MyService(false);
        Thread t = new Thread(() -> myService.lockL());
        t.start();
    }

    /**
     * @throws InterruptedException
     * @see Condition#await()
     * @see Condition#awaitUninterruptibly()
     */
    private static void test10() throws InterruptedException {
        MyService myService = new MyService(false);
        Thread t1 = new Thread(() -> myService.lockJ());
        t1.start();
        Thread.sleep(1000);
        // 抛出 InterruptedException 异常
        t1.interrupt();
        System.out.println("===============");
        Thread t2 = new Thread(() -> myService.lockK());
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }

    /**
     * @see ReentrantLock#tryLock()
     * @see ReentrantLock#tryLock(long, TimeUnit)
     */
    private static void test9() throws InterruptedException {
        MyService myService = new MyService(false);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> myService.lockH()).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> myService.lockI()).start();
        }
    }

    /**
     * @see ReentrantLock#lockInterruptibly()
     */
    private static void test8() {
        MyService myService = new MyService(false);
        Thread t1 = new Thread(() -> myService.lockF());
        t1.start();
        // 正常执行不会抛出异常
        t1.interrupt();

        Thread t2 = new Thread(() -> myService.lockG());
        t2.start();
        // 抛出 InterruptedException 异常
        t2.interrupt();

    }

    /**
     * @see ReentrantLock#isHeldByCurrentThread()
     * @see ReentrantLock#isFair()
     */
    private static void test7() {
        MyService myService = new MyService(false);
        myService.lockE();
    }

    /**
     * @see ReentrantLock#isFair()
     */
    private static void test6() {
        System.out.println(new MyService(false).isFair());
        System.out.println(new MyService(true).isFair());
        System.out.println(new MyService(null).isFair());
    }

    /**
     * @throws InterruptedException
     * @see ReentrantLock#hasWaiters(Condition)
     */
    private static void test5() throws InterruptedException {
        MyService myService = new MyService(false);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> myService.lockD()).start();
        }
        Thread.sleep(3000);
        myService.signalD_2();
    }

    /**
     * @throws InterruptedException
     * @see ReentrantLock#hasQueuedThread(Thread)
     * @see ReentrantLock#hasQueuedThreads()
     */
    private static void test4() throws InterruptedException {
        MyService myService = new MyService(false);
        Thread a = new Thread(() -> myService.lockC());
        Thread b = new Thread(() -> myService.lockC());
        a.start();
        Thread.sleep(1000);
        b.start();
        Thread.sleep(1000);
        System.out.println(myService.getLock().hasQueuedThread(a));
        System.out.println(myService.getLock().hasQueuedThread(b));
        System.out.println(myService.getLock().hasQueuedThreads());
    }

    /**
     * @throws InterruptedException
     * @see ReentrantLock#getWaitQueueLength(Condition)
     */
    private static void test3() throws InterruptedException {
        MyService myService = new MyService(false);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> myService.lockD()).start();
        }
        Thread.sleep(3000);
        myService.signalD();
    }

    /**
     * @throws InterruptedException
     * @see ReentrantLock#getQueueLength()
     */
    private static void test2() throws InterruptedException {
        MyService myService = new MyService(false);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> myService.lockC()).start();
        }
        Thread.sleep(2000);
        System.out.println(myService.getLock().getQueueLength());
    }

    /**
     * @see ReentrantLock#getHoldCount()
     */
    public static void test1() {
        MyService myService = new MyService(false);
        myService.lockA();
    }

    static class MyService {
        private ReentrantLock lock;
        private Condition condition;

        public MyService(Boolean fair) {
            if (fair == null) fair = false;
            this.lock = new ReentrantLock(fair);
            this.condition = lock.newCondition();
        }

        public boolean isFair() {
            return lock.isFair();
        }

        public ReentrantLock getLock() {
            return lock;
        }

        public void lockA() {
            lock.lock();
            System.out.println(lock.getHoldCount());
            lockB();
            lock.unlock();
            System.out.println(lock.getHoldCount());
        }

        private void lockB() {
            lock.lock();
            System.out.println(lock.getHoldCount());
            lock.unlock();
            System.out.println(lock.getHoldCount());
        }

        private void lockC() {
            lock.lock();
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void lockD() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "进入方法");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "结束方法");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalD() {
            lock.lock();
            System.out.println(lock.getWaitQueueLength(condition));
            condition.signal();
            System.out.println(lock.getWaitQueueLength(condition));
            lock.unlock();
        }

        public void signalD_2() {
            lock.lock();
            System.out.println("有没有线程在等待condition？" + lock.hasWaiters(condition)
                    + "等待的线程数是多少？" + lock.getWaitQueueLength(condition));
            //condition.signalAll();
            condition.signal();
            System.out.println("有没有线程在等待condition？" + lock.hasWaiters(condition)
                    + "等待的线程数是多少？" + lock.getWaitQueueLength(condition));
            lock.unlock();
        }

        private void lockE() {
            System.out.println(lock.isHeldByCurrentThread());//false
            System.out.println(lock.isLocked());//false
            lock.lock();
            System.out.println(lock.isHeldByCurrentThread());//true
            System.out.println(lock.isLocked());//true
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            System.out.println(lock.isHeldByCurrentThread());//false
            System.out.println(lock.isLocked());//false
        }

        public void lockF() {
            try {
                lock.lock();
                System.out.println("begin B " + Thread.currentThread().getName());
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String str = new String();
                    Math.random();
                }
                System.out.println("end B " + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }

        public void lockG() {
            try {
                lock.lockInterruptibly();
                System.out.println("begin B " + Thread.currentThread().getName());
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String str = new String();
                    Math.random();
                }
                System.out.println("end B " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void lockH() {
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + "获取锁");
                lock.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + "未获取锁");
            }
        }

        public void lockI() {
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "获取锁");
                    Thread.sleep(100);
                } else {
                    System.out.println(Thread.currentThread().getName() + "未获取锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void lockJ() {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void lockK() {
            lock.lock();
            condition.awaitUninterruptibly();
            lock.unlock();
        }

        public void lockL() {
            try {
                lock.lock();
                System.out.println("begin wait," + System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 10);
                // 小于当前时间立即自动唤醒
                //calendar.add(Calendar.SECOND,-10);
                condition.awaitUntil(calendar.getTime());
                System.out.println("end wait," + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
