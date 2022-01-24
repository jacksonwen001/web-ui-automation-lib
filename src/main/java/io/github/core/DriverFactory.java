package io.github.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.enums.DriverSettings;
import org.openqa.selenium.Dimension;
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

/**
 * @author: Jackson.Wen
 */
public class DriverFactory {
    private final static Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private String browser;
    private String version;
    private String remoteUrl;
    private RemoteWebDriver webDriver;

    public DriverFactory() {
    }

    public DriverFactory(String browser, String version, String remoteUrl) {
        this.browser = browser;
        this.version = version;
        this.remoteUrl = remoteUrl;
    }

    /**
     * 获取 远程服务器 Driver
     *
     * @return
     */
    public RemoteWebDriver getRemoteWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(DriverSettings.BROWSER_NAME.getAttr(), this.browser);
        capabilities.setCapability(DriverSettings.BROWSER_VERSION.getAttr(), this.version);
        Map<String, Object> selenoidCap = new HashMap<>(16);
        selenoidCap.put(DriverSettings.ENABLE_VIDEO.getAttr(), true);
        selenoidCap.put(DriverSettings.ENABLE_VNC.getAttr(), true);
        selenoidCap.put(DriverSettings.DNS_SERVERS_IP.getAttr(), DriverSettings.DNS_SERVERS_IP.getVal());
        capabilities.setCapability(DriverSettings.SELENIDE_OPTION.getAttr(), selenoidCap);
        try {
            webDriver = new RemoteWebDriver(
                    URI.create(remoteUrl).toURL(),
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
    public RemoteWebDriver getDebugDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(DriverSettings.DEBUG_ADDRESS.getAttr(), DriverSettings.DEBUG_ADDRESS.getVal());
        options.addArguments(DriverSettings.START_MAXSIZE.getAttr());
        options.addArguments(DriverSettings.INCOGNITO.getAttr());
        webDriver = new ChromeDriver(options);
        return webDriver;
    }
}
