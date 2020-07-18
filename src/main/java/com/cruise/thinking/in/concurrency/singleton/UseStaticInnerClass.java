package com.cruise.thinking.in.concurrency.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.stream.Stream;

/**
 * 使用静态内置类实现单例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseStaticInnerClass implements Serializable {

    private UseStaticInnerClass() {

    }

    private static class Holder {
        private static UseStaticInnerClass instance = new UseStaticInnerClass();
    }

    public static UseStaticInnerClass getInstance() {
        return Holder.instance;
    }

    protected Object readResolve() throws ObjectStreamException {
        return Holder.instance;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> System.out.println(UseStaticInnerClass.getInstance()));
        }
        Stream.of(threads).forEach(t -> t.start());
    }
}
