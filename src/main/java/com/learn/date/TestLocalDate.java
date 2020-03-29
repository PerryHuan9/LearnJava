package com.learn.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 测试java8 新增java.time 包提供新的时间日期的API， 主要有以下改变：
 * Month的范围用1~12表示1月到12月；
 * Week的范围用1~7表示周一到周日。
 */
public class TestLocalDate {
    /**
     *
     * ┌─────────────┐
     * │LocalDateTime│────┐
     * └─────────────┘    │    ┌─────────────┐
     *                    ├───>│ZonedDateTime│
     * ┌─────────────┐    │    └─────────────┘
     * │   ZoneId    │────┘           ▲
     * └─────────────┘      ┌─────────┴─────────┐
     *                      │                   │
     *                      ▼                   ▼
     *               ┌─────────────┐     ┌─────────────┐
     *               │   Instant   │<───>│    long     │
     *               └─────────────┘     └─────────────┘
     *
     */
    public static void learn() {
        local();
        duration();
        zoneDateTime();
        dateTimeFormatter();
        instant();
        transform();
    }

    public static void log(Object obj) {
        System.out.println(obj);
    }

    /**
     * LocalDate LocalTime LocalDateTime 的输出和解析遵循ISO 8601规定：
     * 注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
     * 日期：yyyy-MM-dd
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     * <p>
     * 注意：因为LocalDateTime没有时区，无法确定某一时刻，LocalDateTime无法与时间戳进行转换，必须得组合时区。
     */
    public static void local() {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        ld = ldt.toLocalDate();
        lt = ldt.toLocalTime();
        log(ld);
        log(lt);
        log(ldt);
        LocalDate ld1 = LocalDate.of(2020, 3, 4);
        LocalTime lt1 = LocalTime.of(22, 23, 34);
        LocalDateTime ldt1 = LocalDateTime.of(2020, 3, 23, 23, 23, 23);
        log(ld1);
        log(lt1);
        log(ldt1);
        LocalDate ld2 = LocalDate.parse("2020-03-23");
        LocalTime lt2 = LocalTime.parse("23:56:34");
        LocalDateTime ldt2 = LocalDateTime.parse("2020-05-23T23:23:23");
        log(ld2);
        log(lt2);
        log(ldt2);
        // 格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        log("日期时间格式化：" + dtf.format(ldt2));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log("日期格式化：" + dateFormat.format(ld2));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        log("时间格式化：" + timeFormatter.format(lt2));
        // 对日期时间进行简单操作
        LocalDateTime localDateTime = LocalDateTime.of(2020, 3, 31, 23, 23);
        log("操作前：" + localDateTime); //2020-03-31T23:23
        log("减掉一个月：" + localDateTime.minusMonths(1)); // 2020-02-29T23:23
        log("加一年减一小时:" + localDateTime.plusYears(1).minusHours(1));  //2021-03-31T22:23
        /**
         * 对日期时间进行调整
         * 调整年：withYear()
         * 调整月：withMonth()
         * 调整日：withDayOfMonth()
         * 调整时：withHour()
         * 调整分：withMinute()
         * 调整秒：withSecond()
         */
        log(localDateTime.withDayOfMonth(12));
        log(localDateTime.withYear(2018));
        log(localDateTime.withMinute(55));
        // 更复杂的调整
        log("今天的0时0刻：" + LocalDate.now().atStartOfDay());
        log("本月第一天：" + localDateTime.with(TemporalAdjusters.firstDayOfMonth()));
        log("本月最后一天：" + localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
        log("下月第一天：" + localDateTime.with(TemporalAdjusters.firstDayOfNextMonth()));
        log("本月第一个周一：" + localDateTime.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        // 比较两个日期或时间的先后
        log(ldt1 + " 在 " + ldt2 + "之前？" + ldt1.isBefore(ldt2));
        log(ld + "在" + ld1 + "之后？" + ld.isAfter(ld1));

    }

    /**
     * Duration表示两个时刻之间的时间间隔。另一个类似的Period表示两个日期之间的天数：
     */
    public static void duration() {
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(2020, 8, 8, 12, 23);
        Duration d = Duration.between(ldt1, ldt2);
        log(ldt1 + " - " + ldt2 + " = " + d);
        log(d.toDays());
        log(d.toMinutes());
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2020, 4, 24);
        Period d1 = Period.between(ld1, ld2);
        log(ld1 + " - " + ld2 + " = " + d1);

    }

    /**
     * 带时区的日期时间 ZoneDateTime
     */
    public static void zoneDateTime() {
        ZonedDateTime zdt = ZonedDateTime.now(); // 当前时区的当前时间
        ZonedDateTime zdt1 = ZonedDateTime.now(ZoneId.of("America/New_York")); // 美国纽约时区的当前时间
        log(zdt);
        log(zdt1);
        LocalDateTime ldt = LocalDateTime.of(2020, 3,30,22, 00);
        ZonedDateTime zonedDateTime = ldt.atZone(ZoneId.systemDefault()); // 当前时区
        ZonedDateTime zonedDateTime1 = ldt.atZone(ZoneId.of("America/New_York")); // 美国时区
        log(zonedDateTime);
        log(zonedDateTime1);
        // 时区转换
        ZonedDateTime usTime = zdt.withZoneSameInstant(ZoneId.of("America/New_York")); // 转纽约时间
        log(usTime);
        log(usTime.toLocalDateTime());
    }

    public static void dateTimeFormatter() {
        ZonedDateTime zdt = ZonedDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZZ");
        log(dtf.format(zdt));

        // 指定语言进行格式化
        var tdf1 = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
        log(tdf1.format(zdt));
        var usFormatter = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
        log(usFormatter.format(zdt));
    }

    /**
     * Instant是新接口用来表示时间戳的类
     */
    public static void instant() {
        Instant instant = Instant.now();
        log(instant);
        log(instant.getEpochSecond()); // 秒数
        log(instant.toEpochMilli()); // 毫秒数
        // 加时区创建ZoneDateTime
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime.toLocalDateTime());
    }

    /**
     * 新旧时间接口的转换
     */
    public static void transform() {
        // 旧接口转新接口
        // 旧接口增加Date和Calendar对象新增了toInstant()方法
        Date date = new Date();
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        log(zonedDateTime);
        Calendar calendar = Calendar.getInstance();
        zonedDateTime = calendar.toInstant().atZone(ZoneId.systemDefault());
        log(zonedDateTime);
        TimeZone timeZone = TimeZone.getDefault();
        ZoneId zoneId = timeZone.toZoneId();

        // 新接口转旧接口
        Instant instant = zonedDateTime.toInstant();
        date = new Date(instant.toEpochMilli());
        log(date);
        TimeZone timeZone1 = TimeZone.getTimeZone(ZoneId.systemDefault().getId());




    }


}
