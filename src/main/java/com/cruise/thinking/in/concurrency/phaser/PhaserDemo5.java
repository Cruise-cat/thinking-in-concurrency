package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#onAdvance(int, int)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#onAdvance(int, int)
 * @since 2020/8/7
 */
public class PhaserDemo5 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2) {
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out
                        .println("protected boolean onAdvance(int phase, int registeredParties)被调用！");
                return true;
                // 返回true不等待了，Phaser呈无效/销毁的状态
                // 返回false则Phaser继续工作
            }
        };

        MyService service = new MyService(phaser);

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }

    static class MyService {
        private Phaser phaser;

        public MyService(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        public void testMethod() {
            try {
                System.out.println("A  begin ThreadName="
                        + Thread.currentThread().getName()
                        + "                              "
                        + System.currentTimeMillis());
                if (Thread.currentThread().getName().equals("B")) {
                    Thread.sleep(5000);
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println("A    end  ThreadName="
                        + Thread.currentThread().getName() + " end phase value="
                        + phaser.getPhase() + " " + System.currentTimeMillis());
                // ////////
                System.out.println("B  begin ThreadName="
                        + Thread.currentThread().getName()
                        + "                              "
                        + System.currentTimeMillis());
                if (Thread.currentThread().getName().equals("B")) {
                    Thread.sleep(5000);
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println("B    end  ThreadName="
                        + Thread.currentThread().getName() + " end phase value="
                        + phaser.getPhase() + " " + System.currentTimeMillis());
                // ////////
                System.out.println("C  begin ThreadName="
                        + Thread.currentThread().getName()
                        + "                              "
                        + System.currentTimeMillis());
                if (Thread.currentThread().getName().equals("B")) {
                    Thread.sleep(5000);
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println("C    end  ThreadName="
                        + Thread.currentThread().getName() + " end phase value="
                        + phaser.getPhase() + " " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class ThreadA extends Thread {

        private MyService myService;

        public ThreadA(MyService myService) {
            super();
            this.myService = myService;
        }

        public void run() {
            myService.testMethod();

        }

    }

    static class ThreadB extends Thread {

        private MyService myService;

        public ThreadB(MyService myService) {
            super();
            this.myService = myService;
        }

        public void run() {
            myService.testMethod();

        }

    }

}

/**
 * return true;
 *
 * 线程A只等待了1次5秒，而在其他的屏障处并未等待，都是快速打印，线程A不再发生阻塞，这就是 onAdvance 返回 true 的效果，取消屏障。
 *
 * A  begin ThreadName=A                              1596805508370
 * A  begin ThreadName=B                              1596805508371
 * protected boolean onAdvance(int phase, int registeredParties)被调用！
 * A    end  ThreadName=B end phase value=-2147483647 1596805513371
 * A    end  ThreadName=A end phase value=-2147483647 1596805513371
 * B  begin ThreadName=B                              1596805513371
 * B  begin ThreadName=A                              1596805513371
 * B    end  ThreadName=A end phase value=-2147483647 1596805513371
 * C  begin ThreadName=A                              1596805513371
 * C    end  ThreadName=A end phase value=-2147483647 1596805513371
 * B    end  ThreadName=B end phase value=-2147483647 1596805518372
 * C  begin ThreadName=B                              1596805518372
 * C    end  ThreadName=B end phase value=-2147483647 1596805523372
 */

/**
 * return false;
 *
 * A  begin ThreadName=A                              1596805341074
 * A  begin ThreadName=B                              1596805341074
 * protected boolean onAdvance(int phase, int registeredParties)被调用！
 * A    end  ThreadName=B end phase value=1 1596805346075
 * A    end  ThreadName=A end phase value=1 1596805346075
 * B  begin ThreadName=A                              1596805346075
 * B  begin ThreadName=B                              1596805346075
 * protected boolean onAdvance(int phase, int registeredParties)被调用！
 * B    end  ThreadName=B end phase value=2 1596805351075
 * B    end  ThreadName=A end phase value=2 1596805351075
 * C  begin ThreadName=B                              1596805351075
 * C  begin ThreadName=A                              1596805351075
 * protected boolean onAdvance(int phase, int registeredParties)被调用！
 * C    end  ThreadName=B end phase value=3 1596805356076
 * C    end  ThreadName=A end phase value=3 1596805356076
 */
