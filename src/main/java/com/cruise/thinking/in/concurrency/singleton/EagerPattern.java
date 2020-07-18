package com.cruise.thinking.in.concurrency.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * 饿汉模式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class EagerPattern {

    private static EagerPattern instance = new EagerPattern();

    private EagerPattern() {
        if (instance != null) {
            throw new RuntimeException("disable create instance by reflect");
        }
    }

    public static EagerPattern getInstance() {
        // 此方法的缺点是由于不是同步方法所以不能对其他变量进行操作，有可能会产生线程安全问题
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> System.out.println(EagerPattern.getInstance()));
        }
        Stream.of(threads).forEach(t -> t.start());

        Class<?> clazz = Class.forName("com.cruise.thinking.in.concurrency.singleton.EagerPattern");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance();
            System.out.println("1111"+newInstance);
        }
    }
}
