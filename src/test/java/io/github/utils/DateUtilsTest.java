package io.github.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.core.BaseConfig;
import io.github.enums.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: Jackson.Wen
 */
@Slf4j
public class DateUtilsTest {
    BaseConfig config = ConfigFactory.create(BaseConfig.class);
    @Test
    public void testDate(){
        String time = "1/24/2022 4:23 PM";
        LocalDateTime localDateTime = LocalDateTimeUtil.parse(time, DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.ENGLISH));

    }
    @Test
    public void testBigDecimal(){
        String number = "-$19.001 (-18% customer)";
        log.info("匹配到： "+BigDecimalUtils.toBigDecimal(number));
    }
    @Test
    public void testList(){
        Queue<String> users = new LinkedBlockingDeque<>();
        String a = "test";
        users.add(a);
        users.add(a);
        users.add(a);
        log.info("How many size: " + users.size());
    }
    @Test
    public void testConfig(){
        if(Boolean.valueOf(config.isDebug())){
            log.info("boolean: " + Boolean.valueOf(config.isDebug()));
        }

    }
    @Test
    public void test(){
        log.info(System.getProperty("os.name"));
    }
}
