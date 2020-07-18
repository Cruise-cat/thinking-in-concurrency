package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 获取根线程组
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class GetRootThreadGroup {

    public static void main(String[] args) {
        // main
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        // system
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());
        // NullPointerException
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent().getName());
    }
}
