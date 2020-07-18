package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date)} 使用示例
 * 计划时间早于当前时间：提前运行的效果
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo3 {

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String dateStr = "2020-07-18 12:31:50";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字符串时间：2020-7-18 12:31:50 当前时间：Sat Jul 18 12:34:56 CST 2020
 * 运行了！时间为：Sat Jul 18 12:34:56 CST 2020
 */