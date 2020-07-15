package com.cruise.thinking.in.concurrency.interview;

/**
 * 面试题 01
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class Question01 {

    /**
     * 执行子类还是Runnable的run方法？
     * 执行子类，因为子类重写了 run 方法
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("执行Runnable匿名内部类run方法");
        }) {
            @Override
            public void run() {
                System.out.println("执行子类run方法");
            }

        };
        t.start();
    }
}
