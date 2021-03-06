package io.github.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Jackson.Wen
 */
public class BigDecimalUtils {
    static final Logger logger = LoggerFactory.getLogger(BigDecimalUtils.class);
    static final Pattern p = Pattern.compile("[+-]?\\d+(\\.\\d+)?");

    public static BigDecimal toBigDecimal(String number) {
        Matcher matcher = p.matcher(number.replaceAll("\\$", ""));
        if (matcher.find()) {
            logger.info(matcher.group());
            return new BigDecimal(matcher.group());
        }
        logger.info("No Number Can Parse To BigDecimal");
        return null;
    }
}
