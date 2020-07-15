package com.cruise.thinking.in.concurrency.threadsafe;

import com.cruise.thinking.in.concurrency.annotation.ThreadUnsafe;

/**
 * 尽管是方法私有变量，但是如果发生逃逸的话也是非线程安全的
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
@ThreadUnsafe
public class PrivateVariableThreadUnSafe {

    public static void main(String[] args) throws InterruptedException {
        MyObject myObject = new MyObject();
        StringBuilder builder = myObject.append();
        System.out.println(builder);
        System.out.println("==========");
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                builder.append(i).append(",");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 30; i < 50; i++) {
                builder.append(i).append(",");
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(3000);
        System.out.println("==========");
        // a300,,31,32,335,36,238339440546,,4,843,,4414,,16,,48,,913,14,15,16,17,18,19,
        System.out.println(builder);
    }


    static class MyObject {

        public StringBuilder append() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("a");
            return stringBuilder;
        }
    }
}
