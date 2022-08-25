package com.shitouren.core.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Autho： 王涛 @DATE： 2018/5/17 15:31
 */
public class DateUtils {
    /**
     * 仅显示年月日，例如 2015-08-11.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT1 = "yyyy.MM.dd";
    /**
     * 显示年月日时分秒，例如 2015-08-11 09:51:53.
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT1 = "yyyy-MM-dd HH:mm";

    /**
     * 仅显示时分秒，例如 09:51:53.
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String TIME_FORMAT1 = "MM-dd HH:mm";
    public static final String TIME_FORMAT2 = "MM月dd日 HH:mm";
    public static final String TIME_FORMAT3 = "MM月dd日";
    public static final String TIME_FORMAT4 = "HH:mm";

    /**
     * 每天的毫秒数 8640000.
     */
    public static final long MILLISECONDS_PER_DAY = 86400000L;

    /**
     * 每周的天数.
     */
    public static final long DAYS_PER_WEEK = 7L;

    /**
     * 每小时毫秒数.
     */
    public static final long MILLISECONDS_PER_HOUR = 3600000L;

    /**
     * 每分钟秒数.
     */
    public static final long SECONDS_PER_MINUTE = 60L;

    /**
     * 每小时秒数.
     */
    public static final long SECONDS_PER_HOUR = 3600L;

    /**
     * 每天秒数.
     */
    public static final long SECONDS_PER_DAY = 86400L;

    /**
     * 每个月秒数，默认每月30天.
     */
    public static final long SECONDS_PER_MONTH = 2592000L;

    /**
     * 每年秒数，默认每年365天.
     */
    public static final long SECONDS_PER_YEAR = 31536000L;

    /**
     * 常用的时间格式.
     */
    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串.
     *
     * @return String 日期字符串，例如2015-08-11
     * @since 1.0
     */
    public static String getDate() {
        return getDate(DateUtils.DATE_FORMAT);
    }

    /**
     * 得到当前时间字符串.
     *
     * @return String 时间字符串，例如 09:51:53
     * @since 1.0
     */
    public static String getTime() {
        return formatDate(new Date(), DateUtils.TIME_FORMAT);
    }

    /**
     * 得到当前日期和时间字符串.
     *
     * @return String 日期和时间字符串，例如 2015-08-11 09:51:53
     * @since 1.0
     */
    public static String getDateTime() {
        return formatDate(new Date(), DateUtils.DATETIME_FORMAT);
    }

    /**
     * 获取当前时间指定格式下的字符串.
     *
     * @param pattern 转化后时间展示的格式，例如"yyyy-MM-dd"，"yyyy-MM-dd HH:mm:ss"等
     * @return String 格式转换之后的时间字符串.
     * @since 1.0
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 获取指定日期的字符串格式.
     *
     * @param date    需要格式化的时间，不能为空
     * @param pattern 时间格式，例如"yyyy-MM-dd"，"yyyy-MM-dd HH:mm:ss"等
     * @return String 格式转换之后的时间字符串.
     * @since 1.0
     */
    public static String getDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 获取日期时间字符串，默认格式为（yyyy-MM-dd）.
     *
     * @param date    需要转化的日期时间
     * @param pattern 时间格式，例如"yyyy-MM-dd" "HH:mm:ss" "E"等
     * @return String 格式转换后的时间字符串
     * @since 1.0
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, DateUtils.DATE_FORMAT);
        }
        return formatDate;
    }

    /**
     * 获取当前年份字符串.
     *
     * @return String 当前年份字符串，例如 2015
     * @since 1.0
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取当前月份字符串.
     *
     * @return String 当前月份字符串，例如 08
     * @since 1.0
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 获取当前天数字符串.
     *
     * @return String 当前天数字符串，例如 11
     * @since 1.0
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 获取当前星期字符串.
     *
     * @return String 当前星期字符串，例如星期二
     * @since 1.0
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }


    /**
     * 将秒转换成天。不足一天舍去
     *
     * @param second
     * @return
     */
    public static int getSecondToDayRounding(Long second) {
        try {
            if (second == null || second <= 0) {
                return 0;
            }
            return (int) (second / 60 / 60 / 24);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 时间差
     */
    public static Map<String, Long> getDatePoor(Date endDate, Date nowDate) {
        Map<String, Long> map = new HashMap<>();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = nowDate.getTime() - endDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        map.put("day", day);
        map.put("hour", hour);
        map.put("min", min);
        map.put("sec", sec);
        System.out.println(min + "分钟" + sec + "秒");
        return map;
    }

    /**
     * 将秒转换成天。不足一天按一天算
     *
     * @param second
     * @return
     */
    public static int getSecondToDay(Long second) {
        try {
            if (second == null || second <= 0) {
                return 0;
            }
            BigDecimal hour = new BigDecimal(second).divide(new BigDecimal(60), 6, BigDecimal.ROUND_HALF_UP)
                    .divide(new BigDecimal(60), 6, BigDecimal.ROUND_HALF_UP);
            BigDecimal[] bigDecimals = hour.divideAndRemainder(new BigDecimal(24));
            int day = hour.divide(new BigDecimal(24), 6, BigDecimal.ROUND_HALF_UP).intValue();
            if (bigDecimals[1].compareTo(new BigDecimal(0)) == 0) {
                return day;
            } else {
                return day + 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据传入时间来获取从传入时间开始到目前一共经过多少天，不足一天按一天算
     *
     * @param date 传入的时间
     * @return
     */
    public static int getAfterDayNum(Date date) {
        Date nowDate = new Date();
        long second = (nowDate.getTime() - date.getTime()) / 1000;
        return getSecondToDay(second);
    }

    /**
     * 字符串转换成date对象
     *
     * @param str     需要转换的字符串
     * @param pattern 字符串的时间格式 比如 字符串为:2017-01-01  需要转换对象则 pattern需要传入 yyyy-MM-dd
     * @return
     */
    public static Date getStrToDate(String str, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将date转换成字符串
     *
     * @param date    需要格式化的时间对象
     * @param pattern 字符串的时间格式 比如 字符串为:2017-01-01  需要转换对象则 pattern需要传入 yyyy-MM-dd
     * @return
     */
    public static String getDateToStr(Date date, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间转换成指定格式的时间， 如 2019-10-10 10:10:10  转换格式为 yyyy-MM-dd 则最终会得到一个date对象为 2019-10-10
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date getDateToDate(Date date, String pattern) {
        String dateToStr = getDateToStr(date, pattern);
        return getStrToDate(dateToStr, pattern);
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date
     * @return
     */
    public static Long getSecondTimestampTwo(Date date) {
        if (null == date) {
            return 0l;
        }
        return date.getTime() / 1000;
    }

    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     */
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取明天的开始时间
     */
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    /**
     * 获取明天的结束时间
     */
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本周的开始时间
     */
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上周的开始时间
     */
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取上周的结束时间
     */
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取本月的开始时间
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取上月的开始时间
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上月的结束时间
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取本年的开始时间
     */
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本年的结束时间
     */
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    /**
     * 获取某一年的开始时间
     */
    public static Date getBeginDayOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取某一年的结束时间
     */
    public static Date getEndDayOfYear(Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    /**
     * 获取某个日期的开始时间
     */
    public static Timestamp getDayStartTime(Date d) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     */
    public static Timestamp getDayEndTime(Date d) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个月的开始时间
     */
    public static Timestamp getMonthStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个月的结束时间
     */
    public static Timestamp getMonthEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(calendar.DATE), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的小时开始时间
     */
    public static Timestamp getHourStartTime(Date d) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的小时结束时间
     */
    public static Timestamp getHourEndTime(Date d) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * 获取指定时间过去的几个小时的的开始时间
     */
    public static Date getPastHoursStartTime(Date d, int past) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY) - past, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 获取指定时间过去的几个小时的的结束时间
     */
    public static Date getPastHoursEndTime(Date d, int past) {
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY) - past, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Date(calendar.getTimeInMillis());
    }
//
//    public static void main(String[] args) {
////        Date hourStartTime = DateUtils.getPastHoursStartTime(new Date(), 2);
////        System.out.println(getDateToStr(hourStartTime, "yyyy-MM-dd HH:mm:ss"));
////        Date pastHoursEndTime = getPastHoursEndTime(new Date(), 2);
////        System.out.println(getDateToStr(pastHoursEndTime, "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(getWeek(new Date()));
//    }


    /**
     * 获取今年是哪一年
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取指定日期是那一月
     */
    public static int getNowMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取指定日期是那一天  0-31
     */
    public static int getNowDay(Date date) {
        Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间的小时数字
     * 如果指定时间为null 则为当前时间
     */
    public static int getNowHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间的分钟数字
     * 如果指定时间为null 则为当前时间
     */
    public static int getNowMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取指定时间的秒数数字
     * 如果指定时间为null 则为当前时间
     */
    public static int getNowSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 两个日期相减得到的天数
     */
    public static int getDiffDays(Date beginDate, Date endDate) {

        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }

        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);

        int days = new Long(diff).intValue();

        return days;
    }

    /**
     * 两个日期相减得到的毫秒数
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    /**
     * 获取两个日期中的最大日期
     */
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    /**
     * 获取两个日期中的最小日期
     */
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    /**
     * 返回某月该季度的第一个月
     */
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }


    /**
     * 返回某个日期下几天的日期
     *
     * @param date
     * @param i    如果为负数， 则获取的是过去的时间
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }


    /**
     * 返回某个日期前几天的日期
     */
    public static Date getFrontDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }


    /**
     * 返回某个日期前几月的日期
     *
     * @param date 开始计算的日期
     * @param i    月份，  1 表示 从 date 开始的前一个月日期对象
     * @return
     */
    public static Date getFrontMonth(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - i);
        return cal.getTime();
    }

    /**
     * 设置日期到指定年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date setYear(Date date, int year) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 根据日期和休息日，获取下一个非休息日的日期
     *
     * @param date  日期
     * @param times 星期集合， 1 = 礼拜1 ，  7 = 礼拜天
     * @return
     */
    public static Date getWordDate(Date date, List<Integer> times) {
        if (!ListUtil.isValidateList(times)) {
            return date;
        }
        if (isRestDay(date, times)) {
            //如果date在休息日内，则获取下一天
            date = DateUtils.getNextDay(date, 1);
            date = getWordDate(date, times);
        }
        return date;
    }

    /**
     * 获取指定时间的星期是否包含在指定的数组中，包含返回true 否则返回fase
     *
     * @param date  指定时间
     * @param times 休息日（ 1 = 礼拜1  7 = 礼拜天）
     * @return
     */
    public static boolean isRestDay(Date date, List<Integer> times) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        int calcw = 0;
        if (w > 1) calcw = w - 1;
        if (w == 1) calcw = 7;
        if (times.contains(calcw)) {
            return true;
        }
        return false;
    }

    /**
     * 获取 date 是礼拜几    1 = 礼拜1   7 = 礼拜天
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        int calcw = 0;
        if (w > 1) calcw = w - 1;
        if (w == 1) calcw = 7;
        return calcw;
    }


    /**
     * 比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
     *
     * @param date1
     * @param date2
     * @param pattern yyyy-MM-dd
     * @return
     */
    public static int compareTo(Date date1, Date date2, String pattern) {
        date1 = getDateToDate(date1, pattern);
        date2 = getDateToDate(date2, pattern);
        if (date1.getTime() > date2.getTime()) {
            return -1;
        } else if (date1.getTime() < date2.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取多少分钟之前或之后的时间  Minute为负数则为之前， 为正数则为之后
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getFrontMinute(Date date, int minute) {
        return new Date(date.getTime() + (minute * 60 * 1000));
    }

    /**
     * 根据时间戳获取时间，如果时间戳为null 则返回当前时间
     *
     * @param timestamp
     * @return
     */
    public static Date getDateByTimestamp(Long timestamp) {
        Date date = null;
        if (timestamp != null && timestamp > 0) {
            date = new Date(timestamp);
        } else {
            date = new Date();
        }
        return date;
    }

    public static Date addYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        //System.out.println("111111111::::"+cal.getTime());
        cal.add(Calendar.YEAR, 1);//增加一年
        //cd.add(Calendar.DATE, n);//增加一天
        //cd.add(Calendar.DATE, -10);//减10天
        //cd.add(Calendar.MONTH, n);//增加一个月
        System.out.println("输出::" + cal.getTime());
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getDateToStr(DateUtils.getDateByTimestamp((long) 1604028000), DATETIME_FORMAT));
    }

    /**
     * 将时间转为固定模式
     *
     * @param date
     * @return
     */
    public static String yyyyMMddhhmmssDate(Date date) {
        return getDateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟" + seconds + "秒";
        } else if (seconds > 0) {
            DateTimes = seconds + "秒";
        }
        return DateTimes;
    }
}
