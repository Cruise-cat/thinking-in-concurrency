package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 使用 synchronized 关键字修饰代码块
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class UseSynchronizedBlock {

    public static void main(String[] args) {
        MyService myService = new MyService();
        //Thread t1 = new Thread(() -> myService.methodA());
        //Thread t2 = new Thread(() -> myService.methodA());
        Thread t1 = new Thread(() -> myService.methodB());
        Thread t2 = new Thread(() -> myService.methodB());
        t1.start();
        t2.start();
    }

    static class MyService {

        private String paramA;
        private String paramB;

        /**
         * synchronized方法由于锁定的是整个方法，性能较差尤其是在方法内部比较耗时的时候，
         * 很多线程会阻塞等待获取锁
         */
        public synchronized void methodA() {
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paramA = "模拟经过长时间运算获取的结果赋值A" + Thread.currentThread().getName();
            paramB = "模拟经过长时间运算获取的结果赋值b" + Thread.currentThread().getName();
            System.out.println(paramA + "   " + paramB);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }

        /**
         * 使用synchronized代码块 改进上个方法提高效率
         * paramGetA和paramGetB是方法的私有变量不会有线程安全问题，只有给实例变量赋值时有线程安全问题
         * 使用synchronized代码块只锁住赋值代码就可
         */
        public void methodB() {
            // 异步执行
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String paramGetA = "模拟经过长时间运算获取的结果赋值A" + Thread.currentThread().getName();
            String paramGetB = "模拟经过长时间运算获取的结果赋值B" + Thread.currentThread().getName();
            synchronized (this) {
                // 同步执行
                this.paramA = paramGetA;
                this.paramB = paramGetB;
            }
            System.out.println(paramA + "   " + paramB);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }

    }
}

