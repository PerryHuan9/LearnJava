package com.learn.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {
    public static void learn() {
        date();
        calendar();
        timeZone();
    }

    public static void log(Object obj) {
        System.out.println(obj);
    }

    public static void date() {
        Date date = new Date();
        log("w:" + (date.getYear() + 1900));
        log("month:" + date.getMonth() + 1);
        log("date:" + date.getDate());
        log("hours:" + date.getHours());
        log("minutes:" + date.getMinutes());
        log("seconds:" + date.getSeconds());
        log("day:" + date.getDay());
        log(date.toLocaleString());
        log(date.toGMTString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        log(sdf.format(date));
        date = new Date(1231332123);
        log(date.toLocaleString());
        try {
            date = sdf.parse("2020-8-8 12:34:34");
            log(date.toLocaleString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = new Date(2020, 10,23, 22, 55,56);
        log(date.toLocaleString());
    }

    public static void calendar() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = 1 + calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        int hh = calendar.get(Calendar.HOUR);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        int dd = calendar.get(Calendar.DAY_OF_WEEK);
        log(y + "-" + m + "-" + d + " " + hh + ":" + mm + ":" + ss + "  " + dd);
        // 如果我们想给它设置成特定的一个日期和时间，就必须先清除所有字段
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置2019年:
        c.set(Calendar.YEAR, 2020);
        // 设置9月:注意8表示9月:
        c.set(Calendar.MONTH, 8);
        // 设置2日:
        c.set(Calendar.DATE, 2);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 23);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log(sdf.format(c.getTime()));
        // calendar对时间进行简单的加减
        // 加5天并减去2小时:
        c.add(Calendar.DAY_OF_MONTH, 5);
        c.add(Calendar.HOUR_OF_DAY, -2);
        // 显示时间:
        System.out.println(sdf.format(d));


    }

    public static void timeZone() {
        TimeZone tzDefault = TimeZone.getDefault();
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00");
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York");
        log(tzDefault.getID());
        log(tzGMT9.getID());
        log(tzNY.getID());
        // 当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.set(2019, 3, 24,23,11,22);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        log(sdf.format(calendar.getTime()));
        log("所有时区："+ Arrays.toString(TimeZone.getAvailableIDs()));


    }

}
