package io.github.core;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseDriver {
    private BaseConfig config = new BaseConfig();
    private final String enableVNC = "enableVNC";
    private final String enableVideo = "enableVideo";
    private final String dns = "dnsServers";
    private final String selenoidOptions = "selenoid:options";
    private final String debugAddress = "debuggerAddress";

    public BaseDriver(){
        Configuration.timeout = config.selenideTimeout();
        Configuration.headless = config.selenideHeadless();
        Configuration.browserSize = config.selenideBrowserSize();
    }

    /**
     * 启动远程服务器
     */
    private void startServerDriver(){
        Configuration.remote = config.selenoidUrl();
        Configuration.reportsFolder = config.selenoidReportFolder();
        Configuration.downloadsFolder = config.selenoidDownloadFolder();
        Map<String, Object> options = new HashMap<>();
        options.put(enableVNC, config.selenoidEnableVnc());
        options.put(enableVideo, config.selenoidEnableVideo());
        options.put(dns, new String[]{config.selenoidDns()});
        Configuration.browserCapabilities = new ChromeOptions();
        Configuration.browserCapabilities.setCapability(selenoidOptions, options);

    }

    /**
     * 启动本地 debug 服务器
     */
    private void startDebugDriver(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(debugAddress, config.selenideDebugAddress());
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }
    /**
     * 根据配置选择启动模式
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
}
