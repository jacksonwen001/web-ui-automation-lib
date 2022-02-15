package io.github.core;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Reloadable;

/**
 * @author: Jackson.Wen
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config.properties")
public interface BaseConfig extends Config, Reloadable {
    BaseConfig BASE_CONFIG = ConfigFactory.create(BaseConfig.class, System.getenv(), System.getProperties());
    /**
     * 浏览器
     *
     * @return
     */
    @Key("browser")
    String browser();

    /**
     * debug 的地址
     *
     * @return
     */
    @Key("browser.debuggerAddress")
    String debuggerAddress();

    /**
     * 浏览器版本
     *
     * @return
     */
    @Key("browser.version")
    String browserVersion();

    /**
     * 浏览器窗口大小
     *
     * @return
     */
    @Key("selenide.browserSize")
    String screenResolution();

    @Key("selenoid.dnsServers")
    String dnsServers();

    /**
     * 是否开启 VNC
     *
     * @return
     */
    @Key("capabilities.enableVNC")
    String capabilitiesVNC();

    /**
     * 是否开启视频录像
     *
     * @return
     */
    @Key("capabilities.enableVideo")
    String capabilitiesVideo();

    /**
     * 远程 selenium-grid 地址。 也就是 selenoid 的地址 http://ip:4444/wd/hub
     *
     * @return
     */
    @Key("remoteUrl")
    String remoteUrl();

    /**
     * 环境变量
     *
     * @return
     */
    @Key("env")
    String env();

    /**
     * 测试用例集合名称
     *
     * @return
     */
    @Key("suite")
    String suite();

    /**
     * 线程数量
     *
     * @return
     */
    @Key("threadCount")
    String threadCount();

    /**
     * 启动本地谷歌浏览器，并设置 debug 模式
     * @return
     */
    @Key("browser.startLocalChrome")
    String startLocalChrome();

    /**
     * 获取本地已经启动的谷歌浏览器信息， 用来判断是否已经启动谷歌浏览器
     * @return
     */
    @Key("browser.getLocalChrome")
    String getLocalChrome();

    /**
     * 是否是 debug 状态
     * @return
     */
    @Key("debug")
    Boolean isDebug();

    /**
     * 超时时间
     * @return
     */
    @Key("selenide.timeout")
    Long selenideTimeout();

    /**
     * 获取用户名
     * @return
     */
    @Key("username")
    String username();

    /**
     * 获取密码
     * @return
     */
    @Key("password")
    String password();

    /**
     * 获取密码
     * @return
     */
    @Key("browser.headless")
    Boolean headless();

    /**
     * 等待 30s 超时
     * @return
     */
    @Key("waitThirtySeconds")
    Long waitThirtySeconds();

    /**
     * 等待 60s 超时
     * @return
     */
    @Key("waitOneMinus")
    Long waitOneMinus();

    /**
     * 等待 120s 超时
     * @return
     */
    @Key("waitTwoMinus")
    Long waitTwoMinus();

    @Key("local")
    Boolean isLocal();
}
