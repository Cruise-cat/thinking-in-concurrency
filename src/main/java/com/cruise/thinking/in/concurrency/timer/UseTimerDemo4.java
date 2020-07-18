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
public class UseTimerDemo4 {

    static class MyTaskA extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    }

    static class MyTaskB extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTaskA myTaskA = new MyTaskA();
            MyTaskB myTaskB = new MyTaskB();
            SimpleDateFormat formatA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String dateStrA = "2020-07-18 12:40:00";
            String dateStrB = "2020-07-18 12:40:30";
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
