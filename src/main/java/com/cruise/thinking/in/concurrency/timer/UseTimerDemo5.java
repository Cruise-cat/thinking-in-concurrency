package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date)} 使用示例
 * 多个 TimerTask 任务及延时的测试
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo5 {

    static class MyTaskA extends TimerTask {

        @Override
        public void run() {
            System.out.println("A开始运行了！时间为：" + new Date());
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A结束运行了！时间为：" + new Date());
        }
    }

    static class MyTaskB extends TimerTask {

        @Override
        public void run() {
            System.out.println("B开始运行了！时间为：" + new Date());
            System.out.println("B结束运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTaskA myTaskA = new MyTaskA();
            MyTaskB myTaskB = new MyTaskB();
            SimpleDateFormat formatA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String dateStrA = "2020-07-18 12:44:30";
            String dateStrB = "2020-07-18 12:45:00";
            Date dateA = formatA.parse(dateStrA);
            Date dateB = formatB.parse(dateStrB);
            System.out.println("字符串A时间：" + dateA.toLocaleString() + " 当前时间：" + new Date().toString());
            System.out.println("字符串B时间：" + dateB.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTaskA, dateA);
            timer.schedule(myTaskB, dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字符串A时间：2020-7-18 12:44:30 当前时间：Sat Jul 18 12:44:23 CST 2020
 * 字符串B时间：2020-7-18 12:45:00 当前时间：Sat Jul 18 12:44:23 CST 2020
 * A开始运行了！时间为：Sat Jul 18 12:44:30 CST 2020
 * A结束运行了！时间为：Sat Jul 18 12:45:20 CST 2020
 * B开始运行了！时间为：Sat Jul 18 12:45:20 CST 2020
 * B结束运行了！时间为：Sat Jul 18 12:45:20 CST 2020
 */