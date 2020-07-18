package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 线程没有指定线程组自动归到当前线程组
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseAutoThreadGroup {

    public static void main(String[] args) {
        System.out.println("A处线程：" + Thread.currentThread().getName()
                + " 所属的线程组名为：" + Thread.currentThread().getThreadGroup().getName()
                + " 中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup group = new ThreadGroup("新的组");

        System.out.println("A处线程：" + Thread.currentThread().getName()
                + " 所属的线程组名为：" + Thread.currentThread().getThreadGroup().getName()
                + " 中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup[] threadGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroup);
        for (int i = 0; i < threadGroup.length; i++) {
            System.out.println("第一个线程组名称为：" + threadGroup[i].getName());
        }

    }
}
