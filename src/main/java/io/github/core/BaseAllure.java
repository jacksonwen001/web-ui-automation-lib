package io.github.core;

import cn.hutool.core.io.FileUtil;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class BaseAllure {
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return screenshot(OutputType.BYTES);
    }

    @Attachment(value = "logs", type = "text/plain")
    public static String saveLog(String name, String logsMessage) {
        return logsMessage;
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String url) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(url)
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl(String url) {
        return url + "/video/" + getSessionId() + ".mp4";
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

}
