package io.github.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.codeborne.selenide.WebDriverRunner;
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

import static com.codeborne.selenide.Selenide.open;

/**
 * @author: Jackson.Wen
 */
public class BaseCookies {
    private static final Logger logger = LoggerFactory.getLogger(BaseCookies.class);
    private BaseConfig config = new BaseConfig();

    /**
     * 存取 cookie 的路径
     * @param name
     * @return
     */
    private Path getCookiePath(String name) {
        return Paths.get(".", "cookies",
                config.env(), name + ".txt");
    }

    /**
     * 存取 cookies
     * @param name
     */
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

    /**
     * 设置 cookies
     * @param name 文件名
     * @throws Exception
     */
    public void setCookie(String name) throws Exception {
        Set<JSON> cookies = getCookies(name);
        for (JSON cookie : cookies) {
            String cookieName = cookie.getByPath("name").toString();
            String val = cookie.getByPath("value").toString();
            logger.info("name: " + cookieName + " val: " + val);
            WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(cookieName, val));
        }
    }

    /**
     * 获取 cookies
     * @param name 存取cookies 的文件名
     * @return
     * @throws Exception
     */
    private Set<JSON> getCookies(String name) throws Exception {
        Path path = getCookiePath(name);
        if (FileUtil.getAttributes(path, false).lastModifiedTime().toInstant()
                .compareTo(Instant.now()) > 8L) {
            throw new Exception("Session Invalid! Please login again!");
        }
        String content = FileUtil.readString(path.toFile(), Charset.defaultCharset());
        Set<JSON> cookies = JSONUtil.parse(content).toBean(Set.class);
        return cookies;
    }

    /**
     * 把 cookie 用于 API 调用
     * @param name
     * @return
     * @throws Exception
     */
    public String getCookiesAsHeader(String name) throws Exception {
        Set<JSON> cookies = getCookies(name);

        StringBuilder sb = new StringBuilder();
        for (JSON cookie : cookies) {
            String cookieName = cookie.getByPath("name").toString();
            String val = cookie.getByPath("value").toString();
            sb.append(cookieName).append("=").append(val).append(";");
        }
        return sb.toString();
    }

    public void openWithCookies(String url, String name) throws Exception {
        open(url);
        setCookie(name);
    }
    public void saveCookies(String name) {
        saveCookie(name);
    }
    public void cleanCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }

}
