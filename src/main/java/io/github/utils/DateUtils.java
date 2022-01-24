package io.github.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.enums.DatePattern;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author: Jackson.Wen
 */
public class DateUtils {

    public static LocalDateTime toLocalDateTime(String time, DatePattern datePattern) {
        return LocalDateTimeUtil.parse(time, datePattern.getPattern());
    }

    public static String toDateTimeString(LocalDateTime localDateTime, DatePattern datePattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(datePattern.getPattern(), Locale.ENGLISH));
    }

    /**
     * UTC 时间转为 中国时间
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime UTCtoChineseTime(LocalDateTime dateTime) {
        return dateTime.plusHours(8L);
    }

    /**
     * UTC 时间转为 中国时间
     *
     * @param dateTime
     * @param datePattern
     * @return
     */
    public static LocalDateTime UTCtoChineseTime(String dateTime, DatePattern datePattern) {
        return toLocalDateTime(dateTime, datePattern).plusHours(8L);
    }


    /**
     * 当前 UTC 时间
     *
     * @return
     */
    public static String currentUTCDateTime() {
        return LocalDateTimeUtil.ofUTC(Instant.now())
                .format(DateTimeFormatter.ofPattern(DatePattern.UTC_TIME.getPattern(), Locale.ENGLISH));
    }

    /**
     * 一天的开始
     *
     * @return
     */
    public static String currentUTCDateStart() {
        return LocalDateTimeUtil.ofDate(LocalDateTimeUtil.ofUTC(Instant.now()))
                .format(DateTimeFormatter.ofPattern(DatePattern.DATE.getPattern(), Locale.ENGLISH)) + "T00:00:00.000Z";
    }

    /**
     * 一天的结束
     *
     * @return
     */
    public static String currentUTCDateEnd() {
        return LocalDateTimeUtil.ofDate(LocalDateTimeUtil.ofUTC(Instant.now()))
                .format(DateTimeFormatter.ofPattern(DatePattern.DATE.getPattern(), Locale.ENGLISH)) + "T23:59:59.000Z";
    }
}
