package com.cruise.thinking.in.concurrency.thread;

/**
 * 暂停和恢复线程
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#suspend()
 * @see Thread#resume()
 * @since 2020/7/15
 */
public class SuspendAndResumeThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);

        myThread.suspend();
        System.out.println("A = " + System.currentTimeMillis() + ",i = " + myThread.getI());
        Thread.sleep(2000);
        System.out.println("A = " + System.currentTimeMillis() + ",i = " + myThread.getI());
        myThread.resume();

        Thread.sleep(2000);

        myThread.suspend();
        System.out.println("B = " + System.currentTimeMillis() + ",i = " + myThread.getI());
        Thread.sleep(2000);
        System.out.println("B = " + System.currentTimeMillis() + ",i = " + myThread.getI());
    }


    static class MyThread extends Thread {
        int i = 0;

        @Override
        public void run() {
            while (true) {
                i++;
            }
        }

        public int getI() {
            return i;
        }
    }
}
