package io.github.enums;


import lombok.Getter;
import org.openqa.selenium.logging.LogType;

import java.util.logging.Level;

/**
 * @author: Jackson.Wen
 */
@Getter
public enum DriverSettings {
    BROWSER_NAME("browserName", ""),
    BROWSER_VERSION("browserVersion", ""),
    SELENIDE_OPTION("selenoid:options", ""),
    DNS_SERVERS_IP("dnsServers", new String[]{"211.138.156.66"}),
    LOCAL_SERVER("", "http://127.0.0.1:5555/wd/hub"),
    START_MAXSIZE("start-maximized", ""),
    INCOGNITO("incognito", ""),
    ALLURE_SELENIDE("AllureSelenide", ""),
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
