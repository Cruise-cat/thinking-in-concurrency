package com.cruise.thinking.in.concurrency.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 方法 {@link Exchanger#exchange(Object)} 具有阻塞性
 *
 * @author Cruise
 * @version 1.0
 * @see Exchanger#exchange(Object)
 * @since 2020/7/25
 */
public class ExchangerDemo1 {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        a.start();
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
                // A 线程将一直阻塞
                System.out.println("在线程 A 中取得线程 B 的值：" + exchanger.exchange("中国人A"));
                System.out.println("A end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
/**
 * main end
 */