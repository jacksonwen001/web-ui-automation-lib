package io.github.enums;

import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.Getter;

import java.util.logging.Level;

/**
 * @author: Jackson.Wen
 */
@Getter
public enum DriverSettings {
    BROWSER_NAME("browserName", ""),
    BROWSER_VERSION("browserVersion", ""),
    SELENIDE_OPTION("selenoid:options", ""),
    DNS_SERVERS_IP("dnsServers", new String[]{"8.8.8.8", "1.1.1.1"}),
    LOCAL_SERVER("", "http://127.0.0.1:5555/wd/hub"),
    START_MAXSIZE("start-maximized", ""),
    INCOGNITO("incognito", ""),
    ALLURE_SELENIDE("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true)
            .includeSelenideSteps(true)
            .enableLogs(LogType.BROWSER, Level.ALL)),
    DEBUG_ADDRESS("debuggerAddress", "127.0.0.1:9222"),
    ENABLE_VNC("enableVNC", true),
    ENABLE_VIDEO("enableVideo", true);

    private final String attr;
    private final Object val;

    DriverSettings(String attr, Object value) {
        this.attr = attr;
        this.val = value;
    }
}
