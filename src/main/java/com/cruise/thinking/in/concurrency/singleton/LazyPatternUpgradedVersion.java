package com.cruise.thinking.in.concurrency.singleton;

import com.sun.javaws.Launcher;

import java.util.stream.Stream;

/**
 * 懒汉模式升级版
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class LazyPatternUpgradedVersion {

    private volatile static LazyPatternUpgradedVersion instance;

    private LazyPatternUpgradedVersion() {

    }

    /**
     * 此方法是线程非安全的，因为{@code new LazyPatternUpgradedVersion()} 是多条字节码指令
     * <li>1.为对象分配内存空间</li>
     * <li>2.初始化对象</li>
     * <li>3.将对象地址赋给 instance 引用</li>
     * 其中第 2 条和第 3 条可能顺序颠倒，这样导致某个线程可能会拿到属性还没有初始化的对象造成 {@link NullPointerException} 等
     * 解决这个问题就需要在 instance 上添加 volatile 修饰，前提是 JDK 5 以后的版本
     *
     * @return
     */
    public static LazyPatternUpgradedVersion getInstance() {
        try {
            if (instance == null) {
                Thread.sleep(2000);
                synchronized (LazyPatternUpgradedVersion.class) {
                    if (instance == null) {
                        instance = new LazyPatternUpgradedVersion();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> System.out.println(LazyPatternUpgradedVersion.getInstance()));
        }
        Stream.of(threads).forEach(t -> t.start());
    }
}
