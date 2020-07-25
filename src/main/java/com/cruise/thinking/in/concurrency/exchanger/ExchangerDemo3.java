package com.cruise.thinking.in.concurrency.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 方法 {@link Exchanger#exchange(Object, long, TimeUnit)} 与超时
 *
 * @author Cruise
 * @version 1.0
 * @see Exchanger#exchange(Object, long, TimeUnit)
 * @since 2020/7/25
 */
public class ExchangerDemo3 {

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
                System.out.println("在线程 A 中取得线程 B 的值：" + exchanger.exchange("中国人A", 2, TimeUnit.SECONDS));
                System.out.println("A end");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } catch (TimeoutException e) {
                System.out.println("TimeoutException");
            }
        }
    }

}
/**
 * main end
 * TimeoutException
 */
