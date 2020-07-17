package com.cruise.thinking.in.concurrency.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者-消费者 操作栈
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ProducerAndConsumer3 {

    public static void main(String[] args) {
        // 1生产者1消费者
        //oneToOne();
        // 多生产者多消费者
         manyToMany();
    }

    public static void oneToOne() {
        MyList myList = new MyList();
        Thread t1 = new Thread(() -> {
            Producer producer = new Producer(myList);
            while (true) producer.addEle();
        }, "t1");
        Thread t2 = new Thread(() -> {
            Consumer consumer = new Consumer(myList);
            while (true) consumer.removeEle();
        }, "t2");
        t1.start();
        t2.start();
    }

    public static void manyToMany() {
        MyList myList = new MyList();
        Thread t1 = new Thread(() -> {
            Producer producer = new Producer(myList);
            while (true) producer.addEle();
        }, "t1");
        Thread t2 = new Thread(() -> {
            Producer producer = new Producer(myList);
            while (true) producer.addEle();
        }, "t2");
        Thread t3 = new Thread(() -> {
            Consumer consumer = new Consumer(myList);
            while (true) consumer.removeEle();
        }, "t3");
        Thread t4 = new Thread(() -> {
            Consumer consumer = new Consumer(myList);
            while (true) consumer.removeEle();
        }, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }


    static class MyList {
        private List<String> list = new ArrayList<>();
        private int maxSize = 10;

        public void add() {
            list.add("java");
        }

        public String remove() {
            return list.remove(0);
        }

        public int getSize() {
            return list.size();
        }

        public boolean isFull() {
            return list.size() == maxSize;
        }

        public boolean isEmpty() {
            return getSize() == 0;
        }
    }

    static class Producer {
        private MyList myList;

        public Producer(MyList myList) {
            this.myList = myList;
        }

        public void addEle() {
            try {
                synchronized (myList) {
                    while (myList.isFull()) {
                        myList.wait();
                    }
                    myList.add();
                    System.out.println(Thread.currentThread().getName() + "," + myList.getSize());
                    Thread.sleep(500);
                    myList.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer {
        private MyList myList;

        public Consumer(MyList myList) {
            this.myList = myList;
        }

        public void removeEle() {
            try {
                synchronized (myList) {
                    while (myList.isEmpty()) {
                        myList.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + "," + myList.remove());
                    Thread.sleep(500);
                    myList.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
