package com.cruise.thinking.in.concurrency.thread;

/**
 * {@link Thread#start()} 与 {@link Runnable#run()} 方法的区别
 * <p>{@link Thread} 实现了 {@link Runnable} 并重写了 run 方法</p>
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#start()
 * @see Thread#run()
 * @since 2020/7/14
 */
public class StartOrRun {

    public static void main(String[] args) {
        // 线程1执行一定先输出，因为是同步的
        executeRun();
        // end2可能先于线程2执行输出，因为是异步的
        executeStart();
    }

    public static void executeRun() {
        Thread t = new Thread(() -> System.out.println("线程1执行"));
        t.run();
        System.out.println("end 1");
    }

    public static void executeStart() {
        Thread t = new Thread(() -> System.out.println("线程2执行"));
        t.start();
        System.out.println("end 2");
    }


}
