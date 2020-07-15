package com.cruise.thinking.in.concurrency.threadsafe;

/**
 * 父子类中的锁重入
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class ReentryLockInherit {

    public static void main(String[] args) {
        Parent p = new Parent();
        Child c = new Child();
        Thread a = new Thread(() -> {
            p.methodA();
        });
        Thread b = new Thread(() -> {
            c.methodB();
        });
        b.setName("b");
        b.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.setName("a");
        a.start();
    }

    /**
     * 当父类方法中使用的是synchronized (this)时，子类对象通过super.methodA()调用父类方法时，
     * 锁对象this仍然是子类对象，所以锁是可重入的。
     * 当父类方法中使用的是synchronized (Parent.class)时，子类对象通过super.methodA()调用父类方法时，
     * 锁对象是父类的class，如果此时父类的锁已经被其他线程拿到则子线程需要等待，此时和锁重入没有关系。
     *
     */
    public static class Parent {
        public void methodA() {
            //synchronized (this) {
                synchronized (Parent.class) {
                System.out.println("parent begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("parent end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            }
        }
    }

    public static class Child extends Parent {
        public synchronized void methodB() {
            System.out.println("child begin " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.methodA();
            System.out.println("child end " + Thread.currentThread().getName());
        }
    }
}
