package com.cruise.thinking.in.concurrency.communication;

/**
 * 生产者-消费者模式 操作值 1:1 交替递增
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA a = new ThreadA(myService);
        ThreadB b = new ThreadB(myService);
        a.start();
        b.start();
    }

    static class MyService {

        int i = 0;

        boolean flag = false;

        public void add() {
            i++;
        }

        public int getI() {
            return i;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public boolean isFlag() {
            return flag;
        }
    }

    static class ThreadA extends Thread {

        private MyService myService;

        public ThreadA(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (myService) {
                        while (myService.isFlag()) {
                            myService.wait();
                        }
                        myService.add();
                        System.out.println(Thread.currentThread().getName() + "," + myService.getI());
                        myService.setFlag(true);
                        Thread.sleep(1000);
                        myService.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {

        private MyService myService;

        public ThreadB(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (myService) {
                        while (!myService.isFlag()) {
                            myService.wait();
                        }
                        myService.add();
                        System.out.println(Thread.currentThread().getName() + "," + myService.getI());
                        myService.setFlag(false);
                        Thread.sleep(1000);
                        myService.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
