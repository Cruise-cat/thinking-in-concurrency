package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#cancel()} 有时不一定会停止执行计划任务，而是正常执行。
 *
 * @author Cruise
 * @version 1.0
 * @see Timer#cancel()
 * @since 2020/7/18
 */
public class UseTimerDemo11 {

    private static int i = 0;

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了！" + i);
        }
    }

    public static void main(String[] args) {
        while (true) {
            try {
                i++;
                MyTask myTask = new MyTask();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = "2020-07-18 14:52:20";
                Timer timer = new Timer();
                Date date = format.parse(dateStr);
                timer.schedule(myTask, date);
                timer.cancel();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 *
 */