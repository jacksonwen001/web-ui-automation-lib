package io.github.core;

import org.aeonbits.owner.ConfigFactory;

import java.util.Optional;

public class TestConfig {
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class);

    public String browser() {
        return config.browser();
    }

    public String debuggerAddress() {
        return config.debuggerAddress();
    }

    public String browserVersion() {
        return config.browserVersion();
    }

    public String dnsServers() {
        return config.dnsServers();
    }

    public Boolean enableVNC() {
        return config.enableVNC();
    }

    public Boolean enableVideo() {
        return config.enableVideo();
    }

    public String remoteUrl() {
        return config.remoteUrl();
    }

    /**
     * get the variable of environment； read from system or configuration file.
     *
     * @return
     */
    public Long selenideTimeout() {
        return config.selenideTimeout();
    }

    public String username() {
        return config.username();
    }

    public String password() {
        return config.password();
    }

    public String env() {
        return Optional.ofNullable(System.getProperty("env")).orElse(config.env());
    }


    public Boolean isDebug() {
        return System.getProperty("isDebug") != null
                ? Boolean.valueOf(System.getProperty("isDebug")) : config.isDebug();
    }

    public Boolean isLocal() {
        return System.getProperty("isLocal") != null
                ? Boolean.valueOf(System.getProperty("isLocal")) : config.isLocal();
    }

    public Boolean headless() {
        return System.getProperty("headless") != null
                ? Boolean.valueOf(System.getProperty("headless")) : config.headless();
    }

    public Boolean isServer() {
        return System.getProperty("isServer") != null
                ? Boolean.valueOf(System.getProperty("isServer")) : config.isServer();
    }
    public String selenoidUI(){
        return config.selenoidUI();
    }
    public String portalUrl() {
        return String.format(config.portalUrl(), env());
    }

    public String customerUrl() {
        return String.format(config.customerUrl(), env());
    }

    public String commandUrl() {
        return String.format(config.commandUrl(), env());
    }

    public String simulatorUrl() {
        return String.format(config.simulatorUrl(), env());
    }
}
