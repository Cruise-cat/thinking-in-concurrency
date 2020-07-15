package com.cruise.thinking.in.concurrency.thread;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * {@link Thread#suspend()} 造成线程不安全示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class SuspendCaseThreadUnsafe {

    public static void main(String[] args) throws InterruptedException {
        Login login = new Login();
        Thread a = new Thread(() -> login.login("b", "bb"), "a");
        a.start();
        Thread.sleep(1000);
        Thread b = new Thread(() -> System.out.println(login.toString()), "b");
        b.start();
    }

    static class Login {
        private String name = "a";
        private String password = "aa";

        public synchronized void login(String name, String password) {
            this.name = name;
            if (Thread.currentThread().getName().equals("a")) {
                System.out.println("a 线程暂停 导致数据同步问题");
                Thread.currentThread().suspend();
            }
            this.password = password;
        }

        @Override
        public String toString() {
            return "Login{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
