package com.cruise.thinking.in.concurrency.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, long)} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Timer#schedule(TimerTask, long)
 * @since 2020/7/18
 */
public class UseTimerDemo12 {

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了！时间为：" + new Date());
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Timer timer = new Timer();
        System.out.println("当前时间：" + new Date().toString());
        timer.schedule(myTask, 6000);
    }
}
/**
 * 当前时间：Sat Jul 18 14:59:27 CST 2020
 * 运行了！时间为：Sat Jul 18 14:59:33 CST 2020
 */