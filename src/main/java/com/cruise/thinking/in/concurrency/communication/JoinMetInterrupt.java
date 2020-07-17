package com.cruise.thinking.in.concurrency.communication;

/**
 * {@link Thread#join()} 遇到 {@link Thread#interrupt()} 方法会抛出异常
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class JoinMetInterrupt {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("t1 s");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 e");
        });

        Thread t2 = new Thread(() -> {
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        // t1 不会受影响
        // t2 会抛出 InterruptedException 异常
        t2.interrupt();
        System.out.println(" main end ");
    }

}
