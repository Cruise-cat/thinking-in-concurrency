package com.cruise.thinking.in.concurrency.threadgroup;

/**
 * 线程组内的线程运行出现异常停止所有线程
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class ThreadGroupExceptionAllStop {

    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("我的线程组");
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(group, "线程" + (i + 1), "1");
            threads[i].start();
        }
        MyThread newT = new MyThread(group, "线程报错", "a");
        newT.start();
    }

    private static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // t 是出现异常的线程
            super.uncaughtException(t, e);
            // 中断当前线程组所有线程
            this.interrupt();
        }
    }

    private static class MyThread extends Thread {
        private String num;

        public MyThread(ThreadGroup group, String name, String num) {
            super(group, name);
            this.num = num;
        }

        /**
         * 方法内部不要有异常 catch 语句，如果有 catch 语句那么 uncaughtException 方法不会执行。
         */
        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            while (this.isInterrupted() == false) {
                System.out.println("死循环中：" + Thread.currentThread().getName());
            }
        }
    }
}
/**
 * 死循环中：线程1
 * 死循环中：线程3
 * 死循环中：线程2
 * 死循环中：线程2
 * 死循环中：线程3
 * 死循环中：线程5
 * 死循环中：线程1
 * 死循环中：线程1
 * 死循环中：线程1
 * 死循环中：线程4
 * 死循环中：线程1
 * 死循环中：线程1
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程7
 * 死循环中：线程8
 * 死循环中：线程8
 * 死循环中：线程8
 * 死循环中：线程8
 * 死循环中：线程8
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程5
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程2
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程3
 * 死循环中：线程5
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程6
 * 死循环中：线程5
 * 死循环中：线程8
 * 死循环中：线程7
 * 死循环中：线程9
 * 死循环中：线程10
 * 死循环中：线程1
 * 死循环中：线程4
 * 死循环中：线程3
 * 死循环中：线程2
 * Exception in thread "线程报错" java.lang.NumberFormatException: For input string: "a"
 * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
 * at java.lang.Integer.parseInt(Integer.java:580)
 * at java.lang.Integer.parseInt(Integer.java:615)
 * at com.cruise.thinking.in.concurrency.threadgroup.ThreadGroupExceptionAllStop$MyThread.run(ThreadGroupExceptionAllStop.java:46)
 */