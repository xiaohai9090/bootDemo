package com.hai.springcloud.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.format.DateParser;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间&日期服务
 */

public class DateUtil {

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");
    public static final int DATE_BY_MONTH = 1; //按月分表
    public static final int DATE_BY_WEEK = 2;    //按周分表
    public static final int DATE_BY_DAY = 3;    //按天分表
    public static final long TIME_ONE_SECOND = 1000; // 秒
    public static final long TIME_ONE_MINUTE = TIME_ONE_SECOND * 60; // 分钟
    public static final long TIME_ONE_HOUR = TIME_ONE_MINUTE * 60; // 小时
    public static final long TIME_ONE_DAY = TIME_ONE_HOUR * 24; // 一天
    public static final long TIME_TWO_DAY = TIME_ONE_HOUR * 48; // 一天
    public static final long TIME_THREE_DAY = TIME_ONE_HOUR * 72; // 三天

    public static final List<String> TIME_ZONE_LIST = new ArrayList();


    public static void main(String[] args) {
        Date now = now("-09:00");
        System.out.println(now);
        ZoneId zoneId = ZoneId.of("UTC" + "-09:00");
        ZoneId zoneId2 = ZoneId.of("UTC" + "+08:00");

        Date timeZoneDate = changeTimeZoneDate(now, zoneId, zoneId2);

        Date date = changeTimeZoneDate(now, ZoneId.of("UTC" + "+08:00"));
        System.out.println(timeZoneDate);
        System.out.println(date);

    }

    /**
     * 当前小时
     *
     * @param date
     * @return
     */
    public static int hourOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.hour(date, true);
    }

    /**
     * 当前分钟
     *
     * @param date
     * @return
     */
    public static int minuteOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.minute(date);
    }

    /**
     * 今天是星期几
     *
     * @param date
     * @return
     */
    public static int dayOfWeek(Date date) {
        return cn.hutool.core.date.DateUtil.dayOfWeek(date) - 1;
    }

    /**
     * 昨天
     *
     * @return
     */
    public static Date yesterday() {
        return cn.hutool.core.date.DateUtil.yesterday().toJdkDate();
    }

    /**
     * 明天
     *
     * @return
     */
    public static Date tomorrow() {
        return cn.hutool.core.date.DateUtil.tomorrow().toJdkDate();
    }

    /**
     * 日期的开始时间 yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static Date beginOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.beginOfDay(date).toJdkDate();

    }

    public static Date offset(Date date, DateField dateField, int offset) {
        return cn.hutool.core.date.DateUtil.offset(date, dateField, offset).toJdkDate();
    }

    /**
     * 今天的开始时间 yyyy-MM-dd 00:00:00
     *
     * @return
     */
    public static Date beginOfToDay() {
        return beginOfDay(now());
    }

    /**
     * 日期的结束时间 yyyy-MM-dd 23:59:59
     *
     * @param date
     * @return
     */
    public static Date endOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.endOfDay(date).toJdkDate();
    }

    /**
     * 今天的结束时间 yyyy-MM-dd 23:59:59
     *
     * @return
     */
    public static Date endOfToDay() {
        return endOfDay(now());
    }

    /**
     * 自定义格式字符串格式化成时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, DateParser pattern) {
        return cn.hutool.core.date.DateUtil.parse(date, pattern).toJdkDate();
    }

    /**
     * yyyy-MM-dd字符串格式化成时间
     *
     * @param date
     * @return
     */
    public static Date parseYMD(String date) {
        return cn.hutool.core.date.DateUtil.parse(date, DatePattern.NORM_DATE_PATTERN).toJdkDate();
    }

    /**
     * yyyyMMdd字符串格式化成时间
     *
     * @param date
     * @return
     */
    public static Date parseYMD2(String date) {
        return cn.hutool.core.date.DateUtil.parse(date, DatePattern.PURE_DATE_FORMAT).toJdkDate();
    }

    /**
     * yyyy-MM-dd HH:mm字符串格式化成时间
     *
     * @param date
     * @return
     */
    public static Date parseYMDHM(String date) {
        return cn.hutool.core.date.DateUtil.parse(date, "yyyy-MM-dd HH:mm").toJdkDate();
    }

    /**
     * yyyy-MM-dd HH:mm:ss字符串格式化成时间
     *
     * @param date
     * @return
     */
    public static Date parseYMDHMS(String date) {
        return cn.hutool.core.date.DateUtil.parse(date, DatePattern.NORM_DATETIME_PATTERN).toJdkDate();
    }

    /**
     * 格式化时间，返回字符串(yyyyMM)
     *
     * @return
     */
    public static String formatYM(Date date) {
        return format(date, "yyyyMM");
    }

    /**
     * 格式化时间，返回字符串(yyyyMMddHHmm)
     *
     * @return
     */
    public static String formatYMDHM(Date date) {
        return format(date, "yyyyMMddHHmm");
    }

    /**
     * 格式化时间，返回字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String formatYMDHMS(Date date) {
        return format(date == null ? now() : date, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 格式化时间，返回字符串(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String formatYMD(Date date) {
        return format(date, DatePattern.PURE_DATE_PATTERN);
    }

    /**
     * 格式化时间，返回字符串(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String formatYMD2(Date date) {
        return format(date, DatePattern.NORM_DATE_PATTERN);
    }

    /**
     * 格式化时间，返回字符串(HHmmss)
     *
     * @param date
     * @return
     */
    public static String formatHMS(Date date) {
        return format(date, DatePattern.PURE_TIME_PATTERN);
    }

    /**
     * 格式化时间，自定义格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return cn.hutool.core.date.DateUtil.format(date, pattern);
    }

    /**
     * 格式化时间，自定义格式化
     *
     * @param pattern
     * @return
     */
    public static String format(String pattern) {
        return format(now(), pattern);
    }

    /**
     * 两个时间的相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenDay(Date startDate, Date endDate) {
        return (int) cn.hutool.core.date.DateUtil.between(startDate, endDate == null ? now() : endDate, DateUnit.DAY);
    }

    /**
     * 时间和现在的相差天数
     *
     * @param date
     * @return
     */
    public static int betweenDayWithNow(Date date) {
        return betweenDay(date, now());
    }

    /**
     * 两个时间的相差分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenMinute(Date startDate, Date endDate) {
        return betweenMinute(startDate, endDate, true);
    }

    /**
     * 两个时间的相差分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenMinute(Date startDate, Date endDate, boolean isAbs) {
        return (int) cn.hutool.core.date.DateUtil.between(startDate, endDate == null ? now() : endDate,
                DateUnit.MINUTE, isAbs);
    }

    /**
     * 时间和现在的相差分钟数
     *
     * @param date
     * @return
     */
    public static int betweenMinuteWithNow(Date date, boolean isAbs) {
        return betweenMinute(date, now(), isAbs);
    }

    /**
     * 时间和现在的相差分钟数
     *
     * @param date
     * @return
     */
    public static int betweenMinuteWithNow(Date date) {
        return betweenMinute(date, now());
    }

    /**
     * 两个时间的相差小时
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenHour(Date startDate, Date endDate) {
        return (int) cn.hutool.core.date.DateUtil.between(startDate, endDate == null ? now() : endDate, DateUnit.HOUR);
    }

    /**
     * 两个时间的相差秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenSecond(Date startDate, Date endDate) {
        return betweenSecond(startDate, endDate, true);
    }

    /**
     * 两个时间的相差秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenSecond(Date startDate, Date endDate, boolean isAbs) {
        return (int) cn.hutool.core.date.DateUtil.between(startDate, endDate == null ? now() : endDate,
                DateUnit.SECOND, isAbs);
    }

    /**
     * 两个时间的相差秒数  有正负
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int betweenMillis(Date startDate, Date endDate) {
        return (int) cn.hutool.core.date.DateUtil.between(startDate, endDate == null ? now() : endDate,
                DateUnit.MS, false);
    }

    /**
     * 日期增加-按日增加
     *
     * @param date
     * @param days
     * @return
     */
    public static Date dateIncreaseByDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 统一转换成毫秒级时间戳
     *
     * @param timestamp
     * @return
     */
    public static long milliseconds(long timestamp) {
        if (String.valueOf(timestamp).length() == 13) {
            return timestamp;
        } else {
            return timestamp * 1000;
        }
    }

    /**
     * 格式化
     *
     * @param timestamp
     * @return
     */
    public static Date timestamp2Date(long timestamp) {
        Instant instant = Instant.ofEpochMilli(milliseconds(timestamp));
        LocalDateTime of = LocalDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
        return Date.from(of.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * 判断两日期是不是同一天
     */
    public static boolean isSameDay(Date d1, Date d2) {
        return cn.hutool.core.date.DateUtil.isSameDay(d1, d2);
    }

    /**
     * 根据分表策略获取分表后缀
     *
     * @param type 分表策略
     * @return
     */
    public static String getTableSuffixByType(String tablePrefix, int type) {
        return getTableSuffixByTypeAndDate(tablePrefix, type, now());
    }

    /**
     * 根据指定日期取定约定的表名后缀
     */
    public static String getTableSuffixByTypeAndDate(String tablePrefix, int type, long time) {
        return getTableSuffixByTypeAndDate(tablePrefix, type, timestamp2Date(time));
    }

    /**
     * 根据指定日期取定约定的表名后缀
     */
    public static String getTableSuffixByTypeAndDate(String tablePrefix, int type, Date date) {
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            date = calendar.getTime();
        }
        if (type == DATE_BY_MONTH) {
            return tablePrefix + "_" + formatYM(date);
        }
        if (type == DATE_BY_WEEK) {
            date = cn.hutool.core.date.DateUtil.beginOfWeek(date);
            return tablePrefix + "_" + formatYMD(date);
        }
        if (type == DATE_BY_DAY) {
            return tablePrefix + "_" + formatYMD(date);
        }
        return null;
    }

    /**
     * 统一的日期
     *
     * @return
     */
    public static Date now() {
        return getTimeZoneDate(DEFAULT_ZONE_ID);
    }

    /**
     * 统一的日期
     *
     * @return
     */
    public static Date now(String zoneID) {
        if (StringUtils.isBlank(zoneID) || zoneID.trim().length() != 6) {
            return now();
        }
        ZoneId zoneId = ZoneId.of("UTC" + zoneID);
        return getTimeZoneDate(zoneId);
    }

    /**
     * 统一的日期
     *
     * @return
     */
    public static long getTime() {
        return now().getTime();
    }

    /**
     * 获取指定时区的当前时间
     *
     * @param zoneId
     * @return
     */
    public static Date getTimeZoneDate(ZoneId zoneId) {
        return Date.from(LocalDateTime.now(zoneId).atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * 获取指定时间转换时区后的时间
     *
     * @param targetZoneId
     * @return
     */
    public static Date changeTimeZoneDate(Date sourceDate, ZoneId targetZoneId) {
        return changeTimeZoneDate(sourceDate, DEFAULT_ZONE_ID, targetZoneId);
    }

    /**
     * 获取东八区时间转换成指定时区后的时间
     *
     * @param sourceDate
     * @param targetZoneId
     * @return
     */
    public static Date changeTimeZoneDate(Date sourceDate, String targetZoneId) {
        return changeTimeZoneDate(sourceDate, DEFAULT_ZONE_ID, ZoneId.of("UTC" + targetZoneId));
    }

    /**
     * 获取指定时区时间转换成其他时区后的时间
     *
     * @param sourceDate
     * @param sourceZoneId
     * @param targetZoneId
     * @return
     */
    public static Date changeTimeZoneDate(Date sourceDate, ZoneId sourceZoneId, ZoneId targetZoneId) {
        return Date.from(LocalDateTime.ofInstant(sourceDate.toInstant(), targetZoneId).atZone(sourceZoneId).toInstant());
    }


    /**
     * 上周六日期
     * @return
     */
    public static Date lastSaturday(String zoneID) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now(zoneID));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        cal.add(Calendar.DATE, -7);
        return endOfDay(cal.getTime());
    }


    /**
     * 上周日日期
     * @return
     */
    public static Date lastSunday(String zoneID) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now(zoneID));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.DATE, -7);
        return beginOfDay(cal.getTime());
    }

}
