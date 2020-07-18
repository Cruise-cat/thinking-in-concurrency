package com.cruise.thinking.in.concurrency.singleton;

import java.util.stream.Stream;

/**
 * 懒汉模式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class LazyPattern {

    private static LazyPattern instance;

    private LazyPattern() {

    }

    /**
     * 此方法的缺点是 synchronized 修饰整个方法，性能查一些
     * @return
     */
    public synchronized static LazyPattern getInstance() {
        if (instance == null) {
            instance = new LazyPattern();
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> System.out.println(LazyPattern.getInstance()));
        }
        Stream.of(threads).forEach(t -> t.start());
    }
}
