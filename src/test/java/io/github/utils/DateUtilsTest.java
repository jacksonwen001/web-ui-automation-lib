package io.github.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author: Jackson.Wen
 */
@Slf4j
public class DateUtilsTest {
    @Test
    public void testDate(){
        String time = "1/24/2022 4:23 PM";
        LocalDateTime localDateTime = LocalDateTimeUtil.parse(time, DateTimeFormatter.ofPattern("M/dd/yyyy h:mm a", Locale.ENGLISH));
    }
    @Test
    public void testBigDecimal(){
        String number = "-$19.001 (-18% customer)";
        log.info("匹配到： "+BigDecimalUtils.toBigDecimal(number));
    }
}
