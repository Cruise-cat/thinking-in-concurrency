package com.cruise.thinking.in.concurrency.threadlocal;

import java.util.Calendar;
import java.util.Date;

/**
 * 使用 {@link InheritableThreadLocal}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseInheritableThreadLocal {

    private static MyInheritableThreadLocal t = new MyInheritableThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(t.get());
        for (int i = 0; i < 10; i++) {
            t.set(new Date());
            new Thread(() -> System.out.println(t.get())).start();
            Thread.sleep(100);
        }
        System.out.println(t.get());
    }

    static class MyInheritableThreadLocal extends InheritableThreadLocal {

        @Override
        protected Object childValue(Object parentValue) {
            return "修改后：" + parentValue;
        }

        @Override
        protected Object initialValue() {
            return new Date();
        }
    }
}
