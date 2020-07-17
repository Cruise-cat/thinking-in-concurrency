package com.cruise.thinking.in.concurrency.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 有缺陷的线程间通信方式，使用轮询的方式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class DefectiveInterThreadCommunicationWay {


    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA a = new ThreadA("a", myList);
        ThreadB b = new ThreadB("b", myList);
        a.start();
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
                while (true) {
                    myList.add();
                    Thread.sleep(100);
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
                // 不断的轮询检查 myList.size()
                while (true) {
                    System.out.println(myList.size());
                    if (myList.size() == 10) {
                        System.out.println("myList.size() == 10 准备退出");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
