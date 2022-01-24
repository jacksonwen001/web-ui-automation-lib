package io.github.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.enums.DatePattern;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author: Jackson.Wen
 */
public class DateUtilsTest {
    @Test
    public void testDate(){
        String time = "1/24/2022 4:23 PM";
        LocalDateTime localDateTime = LocalDateTimeUtil.parse(time, DateTimeFormatter.ofPattern("M/dd/yyyy h:mm a", Locale.ENGLISH));

    }
}
