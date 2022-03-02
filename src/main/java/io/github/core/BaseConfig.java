package io.github.core;

import cn.hutool.core.io.resource.ResourceUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class BaseConfig {
    Properties properties = new Properties();
    private final String env = "env";
    private final String isDebug = "debug";
    private final String isLocal = "local";
    private final String isServer = "server";
    /**
     * selenoid 相关配置
     */
    private final String selenoidPortalUrl = "selenoid.ui.url";
    private final String selenoidHubUrl = "selenoid.hub.url";
    private final String selenoidDns = "selenoid.dns";
    private final String selenoidEnableVnc = "selenoid.enable.vnc";
    private final String selenoidEnableVideo = "selenoid.enable.video";
    private final String selenoidReportFolder = "selenoid.report.folder";
    private final String selenoidDownloadFolder = "selenoid.download.folder";
    /**
     * selenide 相关配置
     */
    private final String selenideTimeout = "selenide.timeout";
    private final String selenideBrowserSize = "selenide.browser.size";
    private final String selenideHeadless = "selenide.browser.headless";
    private final String selenideDebugAddress="selenide.debug.address";
    private final String mustClose = "close";

    /**
     * 默认读取 config.properties 文件
     */
    public BaseConfig() {
        this("config.properties");
    }

    /**
     * 初始化， 读取配置文件
     * @param fileName 配置文件
     */
    public BaseConfig(String fileName) {
        try {
            properties.load(ResourceUtil.getUtf8Reader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得环境变量或者配置文件中的值， 类型为 String
     * @param variableName 变量名
     * @return
     */
    private String getStrValue(String variableName){
        return Optional
                .ofNullable(System.getProperty(variableName))
                .orElse(properties.getProperty(variableName));
    }

    /**
     * 获取环境变量或者配置文件中的值， 类型为 Boolean
     * @param variableName 变量名
     * @return
     */
    private Boolean getBooleanValue(String variableName) {
        return Boolean.valueOf(getStrValue(variableName));
    }

    /**
     * 环境变量
     * @return
     */
    public String env(){
        return getStrValue(env);
    }

    public Boolean mustClose() {
        return getBooleanValue(mustClose);
    }
    /**
     * 是否 debug 模式运行
     * @return
     */
    public Boolean isDebug(){
        return getBooleanValue(isDebug);
    }

    /**
     * 是否本地运行
     * @return
     */
    public Boolean isLocal(){
        return getBooleanValue(isLocal);
    }

    /**
     * 是否服务器运行
     * @return
     */
    public Boolean isServer(){
        return getBooleanValue(isServer);
    }

    /**
     * selenoid ui url
     * @return
     */
    public String selenoidPortalUrl(){
        return getStrValue(selenoidPortalUrl);
    }

    /**
     * selenoid hub url
     * @return
     */
    public String selenoidUrl(){
        return getStrValue(selenoidHubUrl);
    }

    /**
     * selenoid 里面的 dns 有时候会失效， 需要自己指定 dns 服务器
     * @return
     */
    public String selenoidDns(){
        return getStrValue(selenoidDns);
    }

    /**
     *
     * @return
     */
    public Boolean selenoidEnableVnc(){
        return getBooleanValue(selenoidEnableVnc);
    }
    public Boolean selenoidEnableVideo(){
        return getBooleanValue(selenoidEnableVideo);
    }

    public String selenoidReportFolder(){
        return getStrValue(selenoidReportFolder);
    }

    public String  selenoidDownloadFolder(){
        return getStrValue(selenoidDownloadFolder);
    }
    /**
     * selenide timeout
     * @return
     */
    public Long selenideTimeout(){
        return Long.getLong(getStrValue(selenideTimeout));
    }
    /**
     * selenide browser size
     * @return
     */
    public String selenideBrowserSize(){
        return getStrValue(selenideBrowserSize);
    }
    public Boolean selenideHeadless(){
        return getBooleanValue(selenideHeadless);
    }
    public String selenideDebugAddress(){
        return getStrValue(selenideDebugAddress);
    }
}
