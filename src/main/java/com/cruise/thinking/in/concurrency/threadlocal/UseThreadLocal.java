package com.cruise.thinking.in.concurrency.threadlocal;

/**
 * 使用 {@link ThreadLocal}
 *
 * @author Cruise
 * @version 1.0
 * @see ThreadLocal
 * @since 2020/7/17
 */
public class UseThreadLocal {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal = new UseThreadLocal();
        String mainName = Thread.currentThread().getName();
        if (useThreadLocal.threadLocal.get() == null) {
            System.out.println(mainName + "没有存放过值");
        }
        useThreadLocal.threadLocal.set(Thread.currentThread().getName());

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            if (useThreadLocal.threadLocal.get() == null) {
                System.out.println(name + "没有存放过值");
            }
            useThreadLocal.threadLocal.set(name);
            String value = useThreadLocal.threadLocal.get();
            System.out.println(value);
        }).start();

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            if (useThreadLocal.threadLocal.get() == null) {
                System.out.println(name + "没有存放过值");
            }
            useThreadLocal.threadLocal.set(name);
            String value = useThreadLocal.threadLocal.get();
            System.out.println(value);
        }).start();

        String value = useThreadLocal.threadLocal.get();
        System.out.println(value);
    }
}