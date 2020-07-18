package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date, long)} 使用示例
 * 执行任务的时间晚于当前时间：在未来执行的效果
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo6 {

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
            String dateStr = "2020-07-18 14:27:30";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            // 每隔 5s 循环执行
            timer.schedule(myTask, date, 5000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字符串时间：2020-7-18 14:27:30 当前时间：Sat Jul 18 14:27:18 CST 2020
 * 运行了！时间为：Sat Jul 18 14:27:30 CST 2020
 * 运行了！时间为：Sat Jul 18 14:27:35 CST 2020
 * 运行了！时间为：Sat Jul 18 14:27:40 CST 2020
 */