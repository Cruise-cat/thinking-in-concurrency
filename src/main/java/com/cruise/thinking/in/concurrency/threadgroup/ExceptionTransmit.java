package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 线程异常的传递
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class ExceptionTransmit {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    /**
     * 对象的异常处理
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:46)
     */
    public static void test1() {
        MyThread myThread = new MyThread();
        //对象
        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        myThread.start();
    }

    /**
     * 静态的异常处理
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:55)
     */
    public static void test2() {
        MyThread myThread = new MyThread();
        //类
        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
        myThread.start();
    }

    /**
     * 对象的异常处理
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:90)
     */
    public static void test3() {
        MyThreadGroup group = new MyThreadGroup("我的线程组");

        MyThread myThread = new MyThread(group, "我的线程");
        //对象
        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        //类
        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());

        myThread.start();
    }

    /**
     * 静态的异常处理
     * 线程组的异常处理
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:109)
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:109)
     */
    /**
     * 如果想打印必须在 {@link MyThreadGroup#uncaughtException(Thread, Throwable)}
     * 中添加 super.uncaughtException(t, e);
     */
    public static void test4() {
        MyThreadGroup group = new MyThreadGroup("我的线程组");

        MyThread myThread = new MyThread(group, "我的线程");
        //对象
        //myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        //类
        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
        myThread.start();
    }

    /**
     * Exception in thread "我的线程" java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:125)
     * java.lang.NumberFormatException: For input string: "a"
     * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     * at java.lang.Integer.parseInt(Integer.java:580)
     * at java.lang.Integer.parseInt(Integer.java:615)
     * at com.cruise.thinking.in.concurrency.threadgroup.ExceptionTransmit$MyThread.run(ExceptionTransmit.java:125)
     * 线程组的异常处理
     */
    public static void test5() {
        MyThreadGroup group = new MyThreadGroup("我的线程组");

        MyThread myThread = new MyThread(group, "我的线程");
        //对象
        //myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        //类
        //MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
        myThread.start();
    }

    private static class MyThread extends Thread {
        private String num = "a";

        public MyThread() {
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            System.out.println("在线程中打印：" + (numInt + 1));
        }
    }

    private static class MyThreadGroup extends java.lang.ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
            System.out.println("线程组的异常处理");
            e.printStackTrace();
        }
    }

    private static class ObjectUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("对象的异常处理");
            e.printStackTrace();
        }
    }

    private static class StateUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("静态的异常处理");
            e.printStackTrace();
        }
    }
}
