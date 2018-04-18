package com.xfs.timer.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 任务调度帮组类
 * 
 * @author wangdx
 *
 */
public class TaskUtil {

    public static final String STOREDURABLY_TRUE = "1";// 任务运行完后是否保留任务信息 1=保留

    public static final String STOREDURABLY_FALSE = "0";// 任务运行完后是否保留任务信息 0=不保留

    public static final String JOBRUNRECORDFLAG_TRUE = "1";// 任务运行时是否产生任务运行记录：1=产生

    public static final String JOBRUNRECORDFLAG_FALSE = "0";// 任务运行时是否产生任务运行记录：0=不产生

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断指定字符串是否为有值(不为null,并且去除首尾空白字符后的长度大于0)
     * 
     * @param value
     *            指定的字符串
     * @return true-有值; false-无值(为null或去除首尾空白字符后为空)
     */
    public static boolean hasValue(String value) {

        return value != null && value.trim().length() > 0;
    }

    /**
     * 获得系统当前日期时间
     * <p>
     * 时间格式: yyyy-MM-dd HH:mm:ss
     * 
     * @return 返回系统当前日期时间。时间格式: yyyy-MM-dd HH:mm:ss
     */
    public static String getNow() {

        Date curDate = getNowDate();
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(curDate);
    }

    /**
     * 获得系统当前日期时间
     * <p>
     * 时间格式: Wed Nov 05 23:16:40 CST 2008
     * 
     * @return 返回系统当前日期时间
     */
    public static Date getNowDate() {

        Date curDate = new Date(System.currentTimeMillis());
        return curDate;
    }

    /**
     * 获得根据日期字符串和日期格式字符串转换后的Date对象
     * 
     * @param strDate
     *            日期字符串
     * @param format
     *            日期格式字符串
     * @return 返回根据日期字符串和日期格式字符串转换后的Date对象
     */
    public static Date getDate(String strDate, String format) {

        Date date = null;
        format = (format != null && format.length() > 0) ? format : DEFAULT_DATE_FORMAT;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(strDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * 在某一个日期上加天数
     * 
     * @param date
     *            起始日期
     * @param count
     *            需要计算的天数
     * @return 结束日期
     */
    public static Date addDateDay(Date date, int count) {

        return addDateProcessor(GregorianCalendar.DATE, date, count);
    }

    private static Date addDateProcessor(int gregoriancalendar, Date date, int count) {

        GregorianCalendar calendar = getGregorianCalendar(date);
        calendar.add(gregoriancalendar, count);
        return calendar.getTime();
    }

    /**
     * 根据日期返回返回GregorianCalendar对象
     * 
     * @param date
     * @return
     */
    private static GregorianCalendar getGregorianCalendar(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(GregorianCalendar.MONDAY);
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获得指定日期对象格式化为默认日期格式（日期格式：yyyy-MM-dd HH:mm:ss）的字符串
     * 
     * @param date
     *            日期对象
     * @return 返回格式化后的日期字符串
     */
    public static String formatDateTime(Date date) {

        return formatDateTime(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 返回指定日期对象格式化为指定日期格式的字符串
     * 
     * @param date
     *            日期对象
     * @param format
     *            日期格式
     * @return 返回格式化后的日期字符串
     */
    public static String formatDateTime(Date date, String format) {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
