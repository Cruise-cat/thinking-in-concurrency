package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 递归与非递归取得组内对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class IteratorThreadGroup {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup groupA = new ThreadGroup(mainGroup, "A");
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
        ThreadGroup groupB = new ThreadGroup(groupA, "B");
        ThreadGroup[] threadGroupsA = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        // 传入 true 是递归取得子组以及子孙组
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsA, true);
        for (int i = 0; i < threadGroupsA.length; i++) {
            if (threadGroupsA[i] != null) {
                System.out.println(threadGroupsA[i].getName());
            }
        }
        ThreadGroup[] threadGroupsB = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsB, false);
        for (int i = 0; i < threadGroupsB.length; i++) {
            if (threadGroupsB[i] != null) {
                System.out.println(threadGroupsB[i].getName());
            }
        }
    }
}
/**
 * A
 * B
 * A
 */

