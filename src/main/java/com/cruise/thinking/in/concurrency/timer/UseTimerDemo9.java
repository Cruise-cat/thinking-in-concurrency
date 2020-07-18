package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link TimerTask#cancel()} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see TimerTask#cancel()
 * @since 2020/7/18
 */
public class UseTimerDemo9 {

    static class MyTaskA extends TimerTask {

        @Override
        public void run() {
            System.out.println("A运行了！时间为：" + new Date());
            this.cancel();
        }
    }

    static class MyTaskB extends TimerTask {

        @Override
        public void run() {
            System.out.println("B运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTaskA myTaskA = new MyTaskA();
            MyTaskB myTaskB = new MyTaskB();
            SimpleDateFormat formatA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String dateStrA = "2020-07-18 14:41:00";
            String dateStrB = "2020-07-18 14:41:10";
            Date dateA = formatA.parse(dateStrA);
            Date dateB = formatB.parse(dateStrB);
            System.out.println("字符串A时间：" + dateA.toLocaleString() + " 当前时间：" + new Date().toString());
            System.out.println("字符串B时间：" + dateB.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTaskA, dateA, 5000);
            timer.schedule(myTaskB, dateB, 5000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字符串A时间：2020-7-18 14:41:00 当前时间：Sat Jul 18 14:40:26 CST 2020
 * 字符串B时间：2020-7-18 14:41:10 当前时间：Sat Jul 18 14:40:26 CST 2020
 * A运行了！时间为：Sat Jul 18 14:41:00 CST 2020
 * B运行了！时间为：Sat Jul 18 14:41:10 CST 2020
 * B运行了！时间为：Sat Jul 18 14:41:15 CST 2020
 */