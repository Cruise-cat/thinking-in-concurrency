package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 验证 schedule 方法不具有追赶执行性
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo18 {

    private static Timer timer = new Timer();

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("开始运行了！时间为：" + new Date());
            System.out.println("结束运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = "2020-07-18 15:32:30";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date, 5000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
