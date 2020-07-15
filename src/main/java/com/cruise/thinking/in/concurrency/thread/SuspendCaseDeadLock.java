package com.cruise.thinking.in.concurrency.thread;

/**
 * {@link Thread#suspend()} 造成死锁示例
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#suspend()
 * @since 2020/7/15
 */
public class SuspendCaseDeadLock {

    public static void main(String[] args) throws InterruptedException {
        //deadlock1();
        //
        deadlock2();
    }

    private static void deadlock1() throws InterruptedException {
        Lock lock = new Lock();

        Thread t1 = new Thread(() -> lock.print(), "a");
        t1.start();
        Thread.sleep(200);
        // b 线程无法执行 print 方法
        Thread t2 = new Thread(() -> lock.print(), "b");
        t2.start();
    }

    private static void deadlock2() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.suspend();
        // 这行无法被输出，因为执行 suspend 方法后，run 方法内部的 System.out.println(i) 内部的锁没有被释放
        System.out.println("end");
    }

    static class Lock {

        synchronized void print() {
            System.out.println("begin");
            if (Thread.currentThread().getName().equals("a")) {
                System.out.println("a 线程将永远暂停，触发调用 resume 方法");
                Thread.currentThread().suspend();
            }
            System.out.println("end");
        }
    }

    static class MyThread extends Thread {

        int i = 0;

        @Override
        public void run() {
            while (true) {
                i++;
                System.out.println(i);
            }
        }
    }
}
