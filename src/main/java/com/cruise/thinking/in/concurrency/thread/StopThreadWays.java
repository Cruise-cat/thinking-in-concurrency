package com.cruise.thinking.in.concurrency.thread;

/**
 * 停止线程的几种方法
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class StopThreadWays {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        // myThread.interrupt();
        // 暴力停止
        myThread.stop();
//        MyThread2 myThread2 = new MyThread2();
//        myThread2.start();
//        Thread.sleep(1000);
//        myThread2.interrupt();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("跳出循环");
                    // break;
                    return;
                }
                System.out.println(i);
            }
            // 使用 break 语句会执行这行代码
            System.out.println("end");
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 1000000; i++) {
                    if (this.isInterrupted()) {
                        System.out.println("跳出循环");
                        throw new InterruptedException();
                    }
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
