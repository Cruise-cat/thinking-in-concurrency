package com.cruise.thinking.in.concurrency.thread;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * 使用 {@link Thread#stop()} 方法导致的线程不安全
 *
 * @author Cruise
 * @version 1.0
 * @see Thread#stop()
 * @since 2020/7/15
 */
@ThreadUnsafe
public class UseStopCauseThreadUnsafe {

    public static void main(String[] args) throws InterruptedException {
        Login login = new Login();
        MyThread myThread = new MyThread(login);
        myThread.start();
        Thread.sleep(1000);
        myThread.stop();
        System.out.println(login);
    }


    static class Login {
        private String name = "a";
        private String password = "aa";

        public synchronized void login(String name, String password) {
            this.name = name;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    static class MyThread extends Thread {

        private Login login;

        public MyThread(Login login) {
            this.login = login;
        }

        @Override
        public void run() {
            login.login("b", "bb");
        }
    }

}
