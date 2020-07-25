package com.cruise.thinking.in.concurrency.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 方法 {@link Exchanger#exchange(Object)} 传递数据
 *
 * @author Cruise
 * @version 1.0
 * @see Exchanger#exchange(Object)
 * @since 2020/7/25
 */
public class ExchangerDemo2 {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        a.start();
        b.start();
        System.out.println("main end");
    }

    private static class ThreadA extends Thread {
        private final Exchanger<String> exchanger;

        public ThreadA(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println("在线程 A 中取得线程 B 的值：" + exchanger.exchange("中国人A"));
                System.out.println("A end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ThreadB extends Thread {
        private final Exchanger<String> exchanger;

        public ThreadB(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println("在线程 B 中取得线程 A 的值：" + exchanger.exchange("中国人B"));
                System.out.println("B end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
/**
 * main end
 * 在线程 A 中取得线程 B 的值：中国人B
 * 在线程 B 中取得线程 A 的值：中国人A
 * A end
 * B end
 */
