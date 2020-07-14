package com.cruise.thinking.in.concurrency.thread;

/**
 * 证明 Java 的线程是用户线程
 * 打开任务管理器，观察性能->CPU->线程数量在调用 {@link Thread#start()}
 * 方法后瞬间增加 {@link JavaThreadIsUserSpaceThread#count},在所有线程
 * 执行结束后瞬间恢复到启动之前的线程数量
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class JavaThreadIsUserSpaceThread {

    public static final int count = 500;

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {

                }
            });
            // 可以尝试注释此代码观察是否会在系统内创建线程
            thread.start();
        }
    }
}
