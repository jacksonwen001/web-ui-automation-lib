package io.github.core.browsers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.core.TestConfig;
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
    private final TestConfig config = new TestConfig();
    private final Cookies cookies = new Cookies();

    public Drivers() {
        Configuration.timeout = config.selenideTimeout();
        Configuration.headless = config.headless();
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
     * 获取 远程服务器 Driver
     *
     * @return
     */
    @Deprecated
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
        Configuration.remote = config.remoteUrl();
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";

        Map<String, Object> options = new HashMap<>();
        options.put("enableVNC", config.enableVNC());
        options.put("enableVideo", config.enableVideo());
        options.put("enableLog", true);
        options.put(dns, new String[]{config.dnsServers()});

        Configuration.browserCapabilities = new ChromeOptions();
        Configuration.browserCapabilities.setCapability("selenoid:options", options);
    }

    /**
     * 启动调试服务器
     */
    private void startDebugDriver() {
        WebDriverRunner.setWebDriver(getDebugDriver());
    }

    /**
     * 启动 debug 模式或者 server 模式
     */
    public void startDriver() {
        if (config.isDebug()) {
            startDebugDriver();
            return;
        }
        if (config.isLocal()) {
            return;
        }

        if (config.isServer()) {
            startServerDriver();
            return;
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

    public void cleanCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }
}
