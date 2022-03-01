package io.github.core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseDriver {
    TestConfig config = new TestConfig();
    public BaseDriver(){
        Configuration.timeout = config.selenideTimeout();
        Configuration.headless = config.headless();
    }


    public void startLocalDriver(){}

    public void startDebugDriver(){}

}
