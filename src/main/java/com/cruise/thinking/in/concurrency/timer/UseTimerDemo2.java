package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, Date)} 使用示例
 * 执行任务的时间晚于当前时间：在未来执行的效果
 * 创建 Timer 设置守护线程
 *
 * @author Cruise
 * @version 1.0
 * @see Timer#Timer(boolean)
 * @since 2020/7/18
 */
public class UseTimerDemo2 {

    private static Timer timer = new Timer(true);

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
            String dateStr = "2020-07-18 12:31:50";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
