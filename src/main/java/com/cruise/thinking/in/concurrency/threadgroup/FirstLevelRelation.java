package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 线程对象管理线程组：1 级关联
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class FirstLevelRelation {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        ThreadGroup threadGroup = new ThreadGroup("我的线程组");
        Thread threadA = new Thread(threadGroup, myThread);
        Thread threadB = new Thread(threadGroup, myThread);
        threadA.start();
        threadB.start();
        // 2
        System.out.println("活动的线程 " + threadGroup.activeCount());
        // 我的线程组
        System.out.println("线程组的名称 " + threadGroup.getName());
    }

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread-Name: " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
