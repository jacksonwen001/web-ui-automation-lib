package io.github.core;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Reloadable;

/**
 * @author: Jackson.Wen
 * 读取配置文件信息
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config.properties")
public interface BaseConfig extends Config, Reloadable {
    BaseConfig BASE_CONFIG = ConfigFactory.create(BaseConfig.class, System.getenv(), System.getProperties());

    @Key("portalUrl")
    String portalUrl();

    @Key("customerUrl")
    String customerUrl();

    @Key("commandUrl")
    String commandUrl();

    @Key("simulatorUrl")
    String simulatorUrl();

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


    @Key("selenoid.dnsServers")
    String dnsServers();

    /**
     * 是否开启 VNC
     *
     * @return
     */
    @Key("capabilities.enableVNC")
    Boolean enableVNC();

    /**
     * 是否开启视频录像
     *
     * @return
     */
    @Key("capabilities.enableVideo")
    Boolean enableVideo();
    @Key("selenoid.ui.url")
    String selenoidUI();

    /**
     * 远程 selenium-grid 地址。 也就是 selenoid 的地址 http://ip:4444/wd/hub
     *
     * @return
     */
    @Key("remoteUrl")
    String remoteUrl();

    /**
     * 超时时间
     *
     * @return
     */
    @Key("selenide.timeout")
    Long selenideTimeout();

    /**
     * 获取用户名
     *
     * @return
     */
    @Key("username")
    String username();

    /**
     * 获取密码
     *
     * @return
     */
    @Key("password")
    String password();

    /**
     * 获取密码
     *
     * @return
     */
    @Key("browser.headless")
    Boolean headless();

    /**
     * 环境变量
     *
     * @return
     */
    @Key("env")
    String env();


    /**
     * 是否是 debug 状态
     *
     * @return
     */
    @Key("debug")
    Boolean isDebug();

    /**
     * 是否是本地浏览器
     *
     * @return
     */
    @Key("local")
    Boolean isLocal();

    /**
     * 是否在服务器端允许
     *
     * @return
     */
    @Key("server")
    Boolean isServer();

}
