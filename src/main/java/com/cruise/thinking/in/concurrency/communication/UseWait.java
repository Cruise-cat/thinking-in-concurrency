package com.cruise.thinking.in.concurrency.communication;

/**
 * 使用 {@link Object#wait()} 和 {@link Object#wait(long)}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseWait {

    public static void main(String[] args) {
        new MyThread().start();
    }

    static class MyThread extends Thread {

        int i = 0;

        @Override
        public void run() {
            try {
                synchronized (this) {
                    if (i == 0) {
                        System.out.println(System.currentTimeMillis());
                        // 不被换新就一直等待
                        //this.wait();
                        // 2s 内没有被其他线程唤醒则自动唤醒
                        this.wait(2000);
                        System.out.println(System.currentTimeMillis());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
