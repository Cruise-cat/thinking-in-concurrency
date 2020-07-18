package com.cruise.thinking.in.concurrency.singleton;

import java.util.stream.Stream;

/**
 * 使用枚举实现单例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseEnum {

    private UseEnum() {
    }

    public static UseEnum getInstance() {
        return SingleTonEnum.INSTANCE.getInstance();
    }

    private enum SingleTonEnum {
        INSTANCE;
        private UseEnum useEnum;

        SingleTonEnum() {
            useEnum = new UseEnum();
        }

        public UseEnum getInstance() {
            return useEnum;
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> System.out.println(UseEnum.getInstance()));
        }
        Stream.of(threads).forEach(t -> t.start());
    }
}
