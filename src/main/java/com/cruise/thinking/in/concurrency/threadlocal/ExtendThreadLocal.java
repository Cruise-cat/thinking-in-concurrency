package com.cruise.thinking.in.concurrency.threadlocal;

/**
 * 扩展 {@link ThreadLocal}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ExtendThreadLocal {

    private static MyThreadLocal myThreadLocal = new MyThreadLocal();

    public static void main(String[] args) {
        System.out.println(myThreadLocal.get());
    }

    static class MyThreadLocal extends ThreadLocal<String> {
        @Override
        protected String initialValue() {
            return "default";
        }
    }
}
