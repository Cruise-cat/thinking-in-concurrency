package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 组内的线程批量停止
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class BatchStopThread {

    public static void main(String[] args) {
        try {
            ThreadGroup myGroup = new ThreadGroup("myGroup");
            for (int i = 0; i < 5; i++) {
                new MyThread(myGroup, "线程：" + i).start();
            }
            Thread.sleep(3000);
            myGroup.interrupt();
            System.out.println("执行了 myGroup.interrupt()");
        } catch (InterruptedException e) {
            System.out.println("停了");
        }
    }

    private static class MyThread extends Thread {
        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始死循环了");
            while (!this.isInterrupted()) {

            }
            System.out.println(Thread.currentThread().getName() + " 结束了");
        }
    }
}
/**
 * 线程：0 开始死循环了
 * 线程：1 开始死循环了
 * 线程：4 开始死循环了
 * 线程：3 开始死循环了
 * 线程：2 开始死循环了
 * 执行了 myGroup.interrupt()
 * 线程：2 结束了
 * 线程：4 结束了
 * 线程：1 结束了
 * 线程：3 结束了
 * 线程：0 结束了
 */