package io.github.core.browsers;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.codeborne.selenide.WebDriverRunner;
import io.github.core.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: Jackson.Wen
 */
public class Cookies {
    private static final Logger logger = LoggerFactory.getLogger(Cookies.class);
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class);


    private Path getCookiePath(String name) {
        return Paths.get(".", "cookies",
                config.env(), name + ".txt");
    }

    public void saveCookie(String name) {
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        Path path = getCookiePath(name);
        File file = path.toFile();
        if (!FileUtil.exist(file) ||
                FileUtil.getAttributes(path, false).lastModifiedTime().to(TimeUnit.HOURS) > 8L) {
            FileUtil.writeString(
                    JSONUtil.toJsonPrettyStr(cookies),
                    file,
                    Charset.defaultCharset());
        }
    }

    public void setCookie(String name) throws Exception {
        Path path = getCookiePath(name);
        if (FileUtil.getAttributes(path, false).lastModifiedTime().toInstant()
                .compareTo(Instant.now()) > 8L) {
            throw new Exception("Session Invalid! Please login again!");
        }

        String content = FileUtil.readString(path.toFile(), Charset.defaultCharset());
        Set<JSON> cookies = JSONUtil.parse(content).toBean(Set.class);
        for (JSON cookie : cookies) {
            String cookieName = cookie.getByPath("name").toString();
            String val = cookie.getByPath("value").toString();
            logger.info("name: " + cookieName + " val: " + val);
            WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(cookieName, val));
        }
    }
}
