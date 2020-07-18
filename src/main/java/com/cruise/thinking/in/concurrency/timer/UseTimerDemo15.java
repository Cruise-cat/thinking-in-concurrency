package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试 schedule 方法任务延时
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo15 {

    private static Timer timer = new Timer();
    private static int counter = 0;

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            try {
                System.out.println("开始运行了！时间为：" + new Date());
                Thread.sleep(5000);
                System.out.println("结束运行了！时间为：" + new Date());
                counter++;
                if (counter == 5) {
                    timer.cancel();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = "2020-07-18 15:15:00";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date, 2000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
