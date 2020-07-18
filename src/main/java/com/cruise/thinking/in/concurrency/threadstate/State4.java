package com.cruise.thinking.in.concurrency.threadstate;

/**
 * 验证线程的 WAITING 状态
 *
 * @author Cruise
 * @version 1.0
 * @see Thread.State
 * @since 2020/7/18
 */
public class State4 {

    public static void main(String[] args) throws InterruptedException {
        MyService myThread = new MyService();
        Thread t1 = new Thread(() -> myThread.test());
        t1.start();
        Thread.sleep(200);
        // WAITING
        System.out.println("main 方法 t1 中的状态 " + t1.getState());
    }


    private static class MyService {

        private Object object = new Object();

        public void test() {
            try {
                synchronized (object) {
                    System.out.println("线程 " + Thread.currentThread().getName() + "进入方法");
                    object.wait();
                }
            } catch (InterruptedException e) {

            }
        }

    }
}
