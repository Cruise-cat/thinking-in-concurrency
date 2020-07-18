package com.cruise.thinking.in.concurrency.singleton;

import java.io.*;

/**
 * 静态内置类可以达到线程安全问题，但如果遇到序列化对象时，使用默认的方式运行得到的结果还是多例的。
 * 解决办法就是添加 {@link UseStaticInnerClass#readResolve()} 方法
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class SaveAndRead {

    public static void main(String[] args) {
        try {
            UseStaticInnerClass instance = UseStaticInnerClass.getInstance();
            System.out.println(instance);
            FileOutputStream outputStream = new FileOutputStream(new File("UseStaticInnerClass.txt"));
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(instance);
            stream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream inputStream = new FileInputStream(new File("UseStaticInnerClass.txt"));
            ObjectInputStream stream = new ObjectInputStream(inputStream);
            UseStaticInnerClass object = (UseStaticInnerClass) stream.readObject();
            stream.close();
            inputStream.close();
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
