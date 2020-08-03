package com.cruise.thinking.in.concurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@link CyclicBarrier#isBroken()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see CyclicBarrier#isBroken()
 * @since 2020/8/3
 */
public class CyclicBarrierDemo5 {

    public static void main(String[] args) {
        MyService myService = new MyService(new CyclicBarrier(4));
        for (int i = 0; i < 4; i++) {
            new Thread(() -> myService.testMethod()).start();
        }
    }

    static class MyService {
        private CyclicBarrier cyclicBarrier;

        public MyService(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void testMethod() {
            try {
                System.out.println(Thread.currentThread().getName() + "到了，等待其他线程都到了开始起跑");
                if (Thread.currentThread().getName().equals("Thread-2")) {
                    System.out.println("Thread-2 进来了");
                    Thread.sleep(5000);
                    //Integer.parseInt("a");
                    Thread.currentThread().interrupt();
                }
                cyclicBarrier.await();
                System.out.println("都到了，开始跑");
                System.out.println(Thread.currentThread().getName() + "到到终点");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "进入 InterruptedException " + cyclicBarrier.isBroken());
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "进入 BrokenBarrierException " + cyclicBarrier.isBroken());
            }
        }
    }
}
/**
 *
 * Integer.parseInt("a")
 * 一个线程出现异常报错，其他线程继续等待，不影响程序运行的主流程
 *
 * Thread-0到了，等待其他线程都到了开始起跑
 * Thread-3到了，等待其他线程都到了开始起跑
 * Thread-2到了，等待其他线程都到了开始起跑
 * Thread-1到了，等待其他线程都到了开始起跑
 * Thread-2 进来了
 * Exception in thread "Thread-2" java.lang.NumberFormatException: For input string: "a"
 * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
 * 	at java.lang.Integer.parseInt(Integer.java:580)
 * 	at java.lang.Integer.parseInt(Integer.java:615)
 * 	at com.cruise.thinking.in.concurrency.cyclicbarrier.CyclicBarrierDemo5$MyService.testMethod(CyclicBarrierDemo5.java:35)
 * 	at com.cruise.thinking.in.concurrency.cyclicbarrier.CyclicBarrierDemo5.lambda$main$0(CyclicBarrierDemo5.java:18)
 * 	at java.lang.Thread.run(Thread.java:748)
 */


/**
 * Thread.currentThread().interrupt();
 *
 * 类 CyclicBarrier 对于线程的中断处理会使用全有或者全无的破坏模型，意思是
 * 如果有一个线程由于中断或者超时提前离开了屏障点，其他所有在屏障点等待的线程
 * 也会抛出 InterruptedException 或者 BrokenBarrierException 异常，
 * 并且离开屏障点
 *
 * Thread-0到了，等待其他线程都到了开始起跑
 * Thread-3到了，等待其他线程都到了开始起跑
 * Thread-2到了，等待其他线程都到了开始起跑
 * Thread-1到了，等待其他线程都到了开始起跑
 * Thread-2 进来了
 * Thread-2进入 InterruptedException true
 * Thread-1进入 BrokenBarrierException true
 * Thread-3进入 BrokenBarrierException true
 * Thread-0进入 BrokenBarrierException true
 */
