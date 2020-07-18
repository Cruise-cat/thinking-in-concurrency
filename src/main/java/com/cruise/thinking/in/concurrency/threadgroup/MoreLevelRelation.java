package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 线程对象管理线程组：多级关联
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class MoreLevelRelation {

    public static void main(String[] args) {
        // 在 main线程组添加一个线程组 A
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup, "A");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread-Name: " + Thread.currentThread().getName());
                    // 线程必须在运行状态才可以受组管理
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(group, runnable);
        thread.setName("z");
        // 线程必须启动才能加入线程组 A
        thread.start();
        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        //enumerate方法将线程组中的子线程组以复制的形式拷贝到 threadGroups 数组中，并返回拷贝的数量
        Thread.currentThread().getThreadGroup().enumerate(threadGroups);
        System.out.println("main 线程中有多少子线程组：" + threadGroups.length + " 名字为：" + threadGroups[0].getName());
        Thread[] threads = new Thread[threadGroups[0].activeCount()];
        threadGroups[0].enumerate(threads);
        System.out.println(threads[0].getName());
    }

}
/**
 * main 线程中有多少子线程组：1 名字为：A
 * Thread-Name: z
 * z
 */
