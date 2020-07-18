package com.cruise.thinking.in.concurrency.threadstate;

/**
 * 验证线程的 BLOCKED 状态
 *
 * @author Cruise
 * @version 1.0
 * @see Thread.State
 * @since 2020/7/18
 */
public class State3 {

    public static void main(String[] args){
        MyService myThread = new MyService();
        Thread t1 = new Thread(() -> myThread.test());
        Thread t2 = new Thread(() -> myThread.test());
        t1.start();
        t2.start();
        // RUNNABLE
        System.out.println("main 方法 t1 中的状态 " + t1.getState());
        // BLOCKED
        System.out.println("main 方法 t2 中的状态 " + t2.getState());
    }


    private static class MyService {

        public synchronized void test() {
            try {
                System.out.println("线程 " + Thread.currentThread().getName() + "进入方法");
                Thread.sleep(6000);
            } catch (InterruptedException e) {

            }
        }

    }
}
