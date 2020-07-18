package com.cruise.thinking.in.concurrency.sdf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link SimpleDateFormat} 不是线程安全的类
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/18
 */
public class SdfNotThreadSafe {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2000-01-01", "2000-01-02", "2000-01-03", "2000-01-04", "2000-01-05",
                "2000-01-06", "2000-01-07", "2000-01-08", "2000-01-09", "2000-01-10"
        };
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(sdf, dateStringArray[i]);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

    private static class MyThread extends Thread {
        private SimpleDateFormat sdf;
        private String dateString;

        public MyThread(SimpleDateFormat sdf, String dateString) {
            this.sdf = sdf;
            this.dateString = dateString;
        }

        @Override
        public void run() {
            try {
                Date date = sdf.parse(dateString);
                String newDateString = sdf.format(date).toString();
                if (!newDateString.equals(dateString)) {
                    System.out.println("ThreadName = " + this.getName()
                            + "报错了 日期字符串：" + dateString
                            + " 转换成的日期为" + newDateString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * ThreadName = Thread-2报错了 日期字符串：2000-01-03 转换成的日期为1999-12-31
 * ThreadName = Thread-4报错了 日期字符串：2000-01-05 转换成的日期为1999-12-31
 * ThreadName = Thread-7报错了 日期字符串：2000-01-08 转换成的日期为1999-12-31
 * ThreadName = Thread-9报错了 日期字符串：2000-01-10 转换成的日期为1999-12-31
 * ThreadName = Thread-6报错了 日期字符串：2000-01-07 转换成的日期为0601-12-219547
 * ThreadName = Thread-3报错了 日期字符串：2000-01-04 转换成的日期为0601-12-219547
 */