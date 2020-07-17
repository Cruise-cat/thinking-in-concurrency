package com.cruise.thinking.in.concurrency.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 {@link ReentrantLock} 和 {@link Condition} 实现生产者/消费者模式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        MyList myList = new MyList();
        Producer producer = new Producer(myList);
        Consumer consumer = new Consumer(myList);
        Thread t1 = new Thread(() -> {
            producer.addEle();
        }, "t1");
        Thread t2 = new Thread(() -> {
           producer.addEle();
        }, "t2");
        Thread t3 = new Thread(() -> {
            consumer.removeEle();
        }, "t3");
        Thread t4 = new Thread(() -> {
            consumer.removeEle();
        }, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }


    static class MyList {
        private List<String> list = new ArrayList<>();
        private int maxSize = 10;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

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

        public Lock getLock() {
            return lock;
        }

        public Condition getCondition() {
            return condition;
        }
    }

    static class Producer {
        private MyList myList;

        public Producer(MyList myList) {
            this.myList = myList;
        }

        public void addEle() {
            while (true) {
                myList.getLock().lock();
                try {
                    while (myList.isFull()) {
                        myList.getCondition().await();
                    }
                    myList.add();
                    System.out.println(Thread.currentThread().getName() + "," + myList.getSize());
                    Thread.sleep(50);
                    myList.getCondition().signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    myList.getLock().unlock();
                }
            }


        }
    }

    static class Consumer {
        private MyList myList;

        public Consumer(MyList myList) {
            this.myList = myList;
        }

        public void removeEle() {
            while (true) {
                myList.getLock().lock();
                try {
                    while (myList.isEmpty()) {
                        myList.getCondition().await();
                    }
                    System.out.println(Thread.currentThread().getName() + "," + myList.remove());
                    Thread.sleep(50);
                    myList.getCondition().signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    myList.getLock().unlock();
                }
            }


        }
    }
}
