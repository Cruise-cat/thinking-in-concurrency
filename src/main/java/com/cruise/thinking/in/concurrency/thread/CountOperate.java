package com.cruise.thinking.in.concurrency.thread;

/**
 * 复杂的 {@link Thread#currentThread()} 和 {@link Thread#isAlive()}
 * 和 {@link Thread#getId()} 场景
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#currentThread()
 * @see Thread#isAlive()
 * @see Thread#getId()
 * @see Thread#getName()
 * @since 2020/7/15
 */
public class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("CountOperate begin");
        System.out.println("Thread.currentThread().getId() " +
                Thread.currentThread().getId()); // 1
        System.out.println("Thread.currentThread().getName() " +
                Thread.currentThread().getName()); // main
        System.out.println("Thread.currentThread().isAlive() " +
                Thread.currentThread().isAlive()); // true
        System.out.println("this.getId() " + this.getId()); // 12
        System.out.println("this.getName() " + this.getName()); // Thread-0
        System.out.println("this.isAlive() " + this.isAlive()); // false
        System.out.println("CountOperate end");
    }

    /**
     * 此部分代码由t线程调用并不是myThread调用
     */
    @Override
    public void run() {
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getId() " +
                Thread.currentThread().getId());// 13
        System.out.println("Thread.currentThread().getName() " +
                Thread.currentThread().getName());// tt
        System.out.println("Thread.currentThread().isAlive() " +
                Thread.currentThread().isAlive());// true
        System.out.println("this.getId() " + this.getId());// 12
        System.out.println("this.getName() " + this.getName());// Thread-0
        System.out.println("this.isAlive() " + this.isAlive());// false
        System.out.println("run end");
    }

    public static void main(String[] args) {
        CountOperate countOperate = new CountOperate();
        Thread t = new Thread(countOperate);// 由 t 线程执行 Thread-0 线程的 run 方法
        t.setName("tt");
        System.out.println("main begin t id " + t.getId());// 13
        System.out.println("main begin t name " + t.getName());// tt
        System.out.println("main begin t alive " + t.isAlive());// false
        t.start();
        System.out.println("main end t id " + t.getId());// 13
        System.out.println("main end t name " + t.getName());// tt
        System.out.println("main end t alive " + t.isAlive());// true
    }
}
