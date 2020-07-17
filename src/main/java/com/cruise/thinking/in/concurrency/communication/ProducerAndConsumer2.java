package com.cruise.thinking.in.concurrency.communication;

/**
 * 生产者-消费者模式 操作值 交替打印 ★★★★★★★★ 和 ☆☆☆☆☆☆☆☆
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ProducerAndConsumer2 {

    public static void main(String[] args) {
        // 1生产者1消费者
        // oneToOne();
        // 1生产者多消费者
        // oneToMany();
        // 多生产者多消费者
        manyToMany();
    }

    private static void oneToOne(){
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            Producer producer = new Producer(myService);
            while (true) {
                producer.print();
            }
        },"t1");
        Thread t2 = new Thread(() -> {
            Consumer consumer = new Consumer(myService);
            while (true) {
                consumer.print();
            }
        },"t2");
        t1.start();
        t2.start();
    }

    private static void oneToMany(){
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            Producer producer = new Producer(myService);
            while (true) {
                producer.print();
            }
        },"t1");
        Thread t2 = new Thread(() -> {
            Consumer consumer = new Consumer(myService);
            while (true) {
                consumer.print();
            }
        },"t2");
        Thread t3 = new Thread(() -> {
            Consumer consumer = new Consumer(myService);
            while (true) {
                consumer.print();
            }
        },"t3");
        t1.start();
        t2.start();
        t3.start();
    }

    private static void manyToMany(){
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            Producer producer = new Producer(myService);
            while (true) {
                producer.print();
            }
        },"t1");
        Thread t2 = new Thread(() -> {
            Producer producer = new Producer(myService);
            while (true) {
                producer.print();
            }
        },"t2");
        Thread t3 = new Thread(() -> {
            Consumer consumer = new Consumer(myService);
            while (true) {
                consumer.print();
            }
        },"t3");
        Thread t4 = new Thread(() -> {
            Consumer consumer = new Consumer(myService);
            while (true) {
                consumer.print();
            }
        },"t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static class MyService {

        boolean flag = false;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public boolean isFlag() {
            return flag;
        }
    }

    static class Producer {
        private MyService myService;

        public Producer(MyService myService) {
            this.myService = myService;
        }

        public void print() {
            try {
                synchronized (myService) {
                    while (!myService.isFlag()) {
                        myService.wait();
                    }
                    System.out.println(Thread.currentThread().getName()+" ☆☆☆☆☆☆☆☆");
                    myService.setFlag(false);
                    Thread.sleep(1000);
                    myService.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer {
        private MyService myService;

        public Consumer(MyService myService) {
            this.myService = myService;
        }

        public void print() {
            try {
                synchronized (myService) {
                    while (myService.isFlag()) {
                        myService.wait();
                    }
                    System.out.println(Thread.currentThread().getName()+" ★★★★★★★★");
                    myService.setFlag(true);
                    Thread.sleep(1000);
                    myService.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
