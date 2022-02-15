package io.github.core.browsers;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.core.BaseConfig;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author: Jackson.Wen
 */
public class Drivers {
    private static final Logger logger = LoggerFactory.getLogger(Drivers.class);
    private final String debugAddress = "debuggerAddress";
    private final String browserName = "browserName";
    private final String browserVersion = "browserVersion";
    private final String selenoidOptions = "selenoid:options";
    private final String dns = "dnsServers";
    private final String enableVNC = "enableVNC";
    private final String enableVideo = "enableVideo";
    private final String allureSelenide = "AllureSelenide";
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class);
    private final Cookies cookies = new Cookies();

    public Drivers() {
        Configuration.timeout = config.selenideTimeout();
        Configuration.headless = config.headless();
        Configuration.savePageSource = false;
        SelenideLogger.addListener(allureSelenide, new AllureSelenide().screenshots(true));
    }

    /**
     * 获取当前 URL
     *
     * @return
     */
    public static String getUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    /**
     * 获取当前页面 title
     *
     * @return
     */
    public static String getTitle() {
        return WebDriverRunner.getWebDriver().getTitle();
    }

    /**
     * 启动用于 debug 的 chrome
     */
    @SneakyThrows
    private void startChromeForDebug() {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(config.getLocalChrome());
        String message = IoUtil.read(process.getInputStream(), Charset.defaultCharset());
        if (StrUtil.isBlank(message)) {
            runtime.exec(config.startLocalChrome());
        } else {
            logger.error("【NOTE】可能需要关闭开启的 chrome 浏览器， 如果是在调试过程， 请忽略！");
        }
    }

    /**
     * 启动本地chrome，不带 debug 模式
     * @return
     */
//    private WebDriver startLocalChrome(){
////        WebDriverManager.chromedriver().setup();
////        WebDriver webDriver = new ChromeDriver();
////        return webDriver;
//    }

    /**
     * 获取 远程服务器 Driver
     *
     * @return
     */
    private RemoteWebDriver getRemoteWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(browserName, config.browser());
        capabilities.setCapability(browserVersion, config.browserVersion());
        Map<String, Object> selenoidCap = new HashMap<>(16);
        selenoidCap.put(enableVideo, true);
        selenoidCap.put(enableVNC, true);
        selenoidCap.put(dns, new String[]{config.dnsServers()});
        capabilities.setCapability(selenoidOptions, selenoidCap);
        RemoteWebDriver webDriver = null;
        try {
            webDriver = new RemoteWebDriver(
                    URI.create(config.remoteUrl()).toURL(),
                    capabilities
            );
            webDriver.manage()
                    .window()
                    .setSize(new Dimension(1666, 888));

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        return webDriver;
    }

    /**
     * 获取 Debug 的 Driver
     *
     * @return
     */
    private WebDriver getDebugDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(debugAddress, config.debuggerAddress());
        WebDriver webDriver = new ChromeDriver(options);
        return webDriver;
    }

    /**
     * 启动服务器端的浏览器
     */
    private void startServerDriver() {
        WebDriverRunner.setWebDriver(getRemoteWebDriver());
    }

    /**
     * 启动调试服务器
     */
    private void startDebugDriver() {
        startChromeForDebug();
        WebDriverRunner.setWebDriver(getDebugDriver());
    }

    /**
     * 启动 debug 模式或者 server 模式
     */
    public void startDriver() {
        if(config.isLocal() && config.isDebug()) {
            // startLocalChrome();
            return;
        }

        if (config.isDebug()) {
            startDebugDriver();
        } else {
            startServerDriver();
        }
    }

    /**
     * 关闭 driver
     */
    public void closeDriver() {
        closeWindow();
        closeWebDriver();
    }

    /**
     * @param url
     * @param name
     * @throws Exception
     */
    public void openWithCookies(String url, String name) throws Exception {
        open(url);
        cookies.setCookie(name);
    }

    public void saveCookies(String name) {
        cookies.saveCookie(name);
    }
    public void cleanCookies(){
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }
}
