package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 使线程运行具有有序性
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class ThreadRunInOrder {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread a = new MyThread(lock, "A", 1);
        MyThread b = new MyThread(lock, "B", 2);
        MyThread c = new MyThread(lock, "C", 0);
        a.start();
        b.start();
        c.start();
    }

    private static class MyThread extends Thread {
        private Object lock;
        private String showChar;
        private int showNumPosition;
        private int printCount = 0;//统计打印了几个字母
        volatile private static int addNumber = 1;

        public MyThread(Object lock, String showChar, int showNumPosition) {
            this.lock = lock;
            this.showChar = showChar;
            this.showNumPosition = showNumPosition;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    //System.out.println("ThreadName="+ Thread.currentThread().getName()+" get the lock");
                    while (true) {
                        if (addNumber % 3 == showNumPosition) {
                            System.out.println("ThreadName=" + Thread.currentThread().getName()
                                    + " runCount = " + addNumber + " " + showChar);
                            lock.notifyAll();
                            addNumber++;
                            printCount++;
                            if (printCount == 3) {
                                break;
                            }
                        } else {
                            //System.out.println("ThreadName="+ Thread.currentThread().getName()+" will await");
                            lock.wait();
                            //System.out.println("ThreadName="+ Thread.currentThread().getName()+" after await");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * ThreadName=Thread-0 runCount = 1 A
 * ThreadName=Thread-1 runCount = 2 B
 * ThreadName=Thread-2 runCount = 3 C
 * ThreadName=Thread-0 runCount = 4 A
 * ThreadName=Thread-1 runCount = 5 B
 * ThreadName=Thread-2 runCount = 6 C
 * ThreadName=Thread-0 runCount = 7 A
 * ThreadName=Thread-1 runCount = 8 B
 * ThreadName=Thread-2 runCount = 9 C
 */
