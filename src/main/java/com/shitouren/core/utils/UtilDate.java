/**
 * Sumpay.cn.
 * Copyright (c) 2007-2015 All Rights Reserved.
 */
package com.shitouren.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author pangxf
 * @version $Id: UtilDate.java, v 0.1 2015-11-30 下午3:03:06 pangxf Exp $
 */
public class UtilDate {

    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong  = "yyyyMMddHHmmss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple  = "yyyy-MM-dd HH:mm:ss";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort = "yyyyMMdd";

    /**
     * 返回系统当前时间(精确到毫秒),订单时间
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 产生随机的三位数
     * @return
     */
    public static String getThree() {
        Random rad = new Random();
        return rad.nextInt(1000) + "";
    }
}
