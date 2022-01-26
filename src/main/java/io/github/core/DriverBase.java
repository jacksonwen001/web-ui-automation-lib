package io.github.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.enums.DriverSettings;
import io.github.utils.ConfigUtils;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.*;
import static io.github.utils.ConfigUtils.ENV;

/**
 * @author: Jackson.Wen
 */
public class DriverBase {
    static final Logger logger = LoggerFactory.getLogger(DriverBase.class);
    ConfigUtils config = new ConfigUtils();
    private final String browser = System.getProperty("browser") == null
            ? config.get("driver", "browser")
            : System.getProperty("browser");

    private final String version = System.getProperty("version") == null
            ? config.get("driver", "version")
            : System.getProperty("version");

    private final String remoteUrl = System.getProperty("remoteUrl") == null
            ? config.get("driver", "remoteUrl")
            : System.getProperty("remoteUrl");

    private final Boolean isServer = System.getProperty("sever") != null;

    private final Integer timeout = Integer.valueOf(config.get("driver", "timeout"));
    private WebDriver webDriver;

    public DriverBase() {
        Configuration.timeout = timeout;
        SelenideLogger.addListener(DriverSettings.ALLURE_SELENIDE.getAttr(), new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(LogType.BROWSER, Level.ALL));
    }

    public void startDriver() {
        if (isServer) {
            DriverFactory driverFactory = new DriverFactory(browser, version, remoteUrl);
            webDriver = driverFactory.getRemoteWebDriver();
            WebDriverRunner.setWebDriver(webDriver);
            return;
        }
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.getDebugDriver();
        WebDriverRunner.setWebDriver(webDriver);
    }

    public void closeDriver() {
        closeWindow();
        closeWebDriver();
    }

    private Path getCookiePath(String name){
        return Paths.get(".", "cookies", ENV, name + ".txt");
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

    private void setCookie(String name) throws Exception {
        Path path = getCookiePath(name);
        logger.info("time: " + (FileUtil.getAttributes(path, false).lastModifiedTime().toInstant()));
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

    public void openAndSetCookie(String url, String name) throws Exception {
        open(url);
        setCookie(name);
    }
}
