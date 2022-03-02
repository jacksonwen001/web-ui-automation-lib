package io.github.core;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * @author jackson wen
 */
public class BaseTest {
    protected BaseConfig config = new BaseConfig();

    @BeforeClass(description = "Start browser")
    public void beforeClass(){
        BaseDriver baseDriver = new BaseDriver();
        baseDriver.startDriver();
    }

    @AfterClass(description = "Close browser")
    public void afterClass(){
        // debug 默认不关闭浏览器
        if(config.isDebug()){
            return;
        }
        // 服务器或者指定 mustClose 时才关闭
        if(config.isServer() || config.mustClose()) {
            Selenide.closeWebDriver();
        }
    }
}
