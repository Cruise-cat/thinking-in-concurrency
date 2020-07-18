package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date, long)} 使用示例
 * 任务执行时间被延时
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo8 {

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("开始运行了！时间为：" + new Date());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timer timer = new Timer();
            String dateStr = "2020-07-18 14:35:10";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date, 3000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
