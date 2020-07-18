package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date, long)} 使用示例
 * 计划时间早于当前时间：提前执行的效果
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo7 {

    private static Timer timer = new Timer();

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
            String dateStr = "2020-07-18 14:27:30";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            // 指定时间早于当前时间立即执行，每隔 5s 循环执行
            timer.schedule(myTask, date, 5000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 *
 */