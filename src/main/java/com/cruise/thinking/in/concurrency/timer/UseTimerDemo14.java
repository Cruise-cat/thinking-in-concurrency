package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试 schedule 方法任务不延时
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class UseTimerDemo14 {

    private static Timer timer = new Timer();
    private static int counter = 0;

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            try {
                System.out.println("开始运行-了！时间为：" + new Date());
                Thread.sleep(1000);
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
            String dateStr = "2020-07-18 15:11:30";
            Date date = format.parse(dateStr);
            System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间：" + new Date().toString());
            timer.schedule(myTask, date, 3000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 字符串时间：2020-7-18 15:11:30 当前时间：Sat Jul 18 15:11:23 CST 2020
 * 开始运行了！时间为：Sat Jul 18 15:11:30 CST 2020
 * 结束运行了！时间为：Sat Jul 18 15:11:31 CST 2020
 * 开始运行了！时间为：Sat Jul 18 15:11:33 CST 2020
 * 结束运行了！时间为：Sat Jul 18 15:11:34 CST 2020
 * 开始运行了！时间为：Sat Jul 18 15:11:36 CST 2020
 * 结束运行了！时间为：Sat Jul 18 15:11:37 CST 2020
 * 开始运行了！时间为：Sat Jul 18 15:11:39 CST 2020
 * 结束运行了！时间为：Sat Jul 18 15:11:40 CST 2020
 * 开始运行了！时间为：Sat Jul 18 15:11:42 CST 2020
 * 结束运行了！时间为：Sat Jul 18 15:11:43 CST 2020
 */