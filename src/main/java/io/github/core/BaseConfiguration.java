package io.github.core;

import org.aeonbits.owner.ConfigFactory;

import java.util.Optional;

public class BaseConfiguration {
    private BaseConfig config = ConfigFactory.create(BaseConfig.class);

    public String browser() {
        return config.browser();
    }

    public String debuggerAddress() {
        return config.debuggerAddress();
    }

    
    public String browserVersion() {
        return config.browserVersion();
    }

    
    public String screenResolution() {
        return config.screenResolution();
    }

    
    public String dnsServers() {
        return config.dnsServers();
    }

    
    public String capabilitiesVNC() {
        return config.capabilitiesVNC();
    }

    
    public String capabilitiesVideo() {
        return config.capabilitiesVideo();
    }

    
    public String remoteUrl() {
        return config.remoteUrl();
    }

    /**
     * get the variable of environment； read from system or configuration file.
     * @return
     */
    public String env(){
        return Optional.ofNullable(System.getProperty("env")).orElse(config.env());
    }

    
    public String suite() {
        return config.suite();
    }

    
    public String threadCount() {
        return config.threadCount();
    }

    
    public String startWindowsLocalChrome() {
        return config.startWindowsLocalChrome();
    }

    
    public String getWindowsLocalChrome() {
        return config.getWindowsLocalChrome();
    }

    
    public String startMacLocalChrome() {
        return config.startMacLocalChrome();
    }

    
    public String getMacLocalChrome() {
        return config.getMacLocalChrome();
    }

    
    public Boolean isDebug() {
        return config.isDebug();
    }

    
    public Long selenideTimeout() {
        return config.selenideTimeout();
    }

    
    public String username() {
        return config.username();
    }

    
    public String password() {
        return config.password();
    }

    
    public Boolean headless() {
        return config.headless();
    }

    
    public Long waitThirtySeconds() {
        return config.waitThirtySeconds();
    }

    
    public Long waitOneMinus() {
        return config.waitOneMinus();
    }

    
    public Long waitTwoMinus() {
        return config.waitTwoMinus();
    }

    
    public Boolean isLocal() {
        return config.isLocal();
    }

    
    public String platform() {
        return config.platform();
    }

}
