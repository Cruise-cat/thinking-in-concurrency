package com.cruise.thinking.in.concurrency.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 正确的实现线程间通信的方式
 * 使用 wait & notify
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class CorrectInterThreadCommunicationWay {

    public static void main(String[] args) throws InterruptedException {
        MyList myList = new MyList();
        ThreadA a = new ThreadA("a", myList);
        ThreadB b = new ThreadB("b", myList);
        a.start();
        Thread.sleep(50);
        b.start();
    }

    static class MyList {
        private List<String> list = new ArrayList<>();

        public void add() {
            list.add("Cruise");
        }

        public int size() {
            return list.size();
        }
    }

    static class ThreadA extends Thread {
        private MyList myList;

        public ThreadA(String name, MyList myList) {
            super(name);
            this.myList = myList;
        }

        @Override
        public void run() {
            try {
                synchronized (myList) {
                    for (int i = 0; i < 10; i++) {
                        myList.add();
                        if (myList.size() == 5) {
                            myList.notifyAll();
                            break;
                        }
                        System.out.println(myList.size());
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class ThreadB extends Thread {
        private MyList myList;

        public ThreadB(String name, MyList myList) {
            super(name);
            this.myList = myList;
        }

        @Override
        public void run() {
            try {
                synchronized (myList) {
                    if (myList.size() != 5) {
                        myList.wait();
                    }
                    System.out.println("已经添加" + myList.size() + "个");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
