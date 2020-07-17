package com.cruise.thinking.in.concurrency.communication;

/**
 * 使用 {@link Thread#join()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("sub thread end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        // main 线程无限期阻塞等待此线程销毁
        // thread.join();
        // main 线程阻塞 1s 等待此线程销毁
        thread.join(1000);
        System.out.println("main thread end");
    }
}
