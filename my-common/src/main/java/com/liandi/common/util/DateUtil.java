package com.liandi.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author czg
 * @date 2020/5/11 14:40
 */
public class DateUtil {

    private DateUtil() {}

    /**
     * 日期时间格式。yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式。yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 日期变更
     * 
     * @param date
     * @param years
     * @param months
     * @param days
     * @return
     */
    public static Date plus(Date date, long years, long months, long days) {
        return localDateTime2Date(date2LocalDateTime(date).plusYears(years).plusMonths(months).plusDays(days));
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @param formatter
     * @return
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        return date2LocalDateTime(date).format(formatter);

    }

    /**
     * 字符转成日期时间对象
     * 
     * @param text
     * @param formatter
     * @return
     */
    public static Date parseDatetime(String text, DateTimeFormatter formatter) {
        return localDateTime2Date(LocalDateTime.parse(text, formatter));
    }

    /**
     * 字符转成日期对象
     * 
     * @param text
     * @param formatter
     * @return
     */
    public static Date parseDate(String text, DateTimeFormatter formatter) {
        return localDate2Date(LocalDate.parse(text, formatter));
    }

    /**
     * LocalDate 转成 Date
     * 
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转成 Date
     * 
     * @param localDateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转成 LocalDateTime
     * 
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
