package com.cruise.thinking.in.concurrency.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link Timer#schedule(TimerTask, long, long)} 使用示例
 *
 * @author Cruise
 * @version 1.0
 * @see Timer#schedule(TimerTask, long, long)
 * @since 2020/7/18
 */
public class UseTimerDemo13 {

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
        timer.schedule(myTask,2000, 6000);
    }
}
