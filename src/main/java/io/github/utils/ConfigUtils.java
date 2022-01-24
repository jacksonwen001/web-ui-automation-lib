package io.github.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.setting.Setting;

/**
 * @author: Jackson.Wen
 */
public class ConfigUtils {
    public static String ENV = System.getProperty("env") == null ? "qa" : System.getProperty("env");
    private Setting app;

    public ConfigUtils(){
        this.app = new Setting("app.setting");
    }

    public ConfigUtils(String name) {
        String fileName = String.format("%s/%s.setting", ENV, name);
        this.app = new Setting(fileName);
    }

    public String get(String key){
        return app.get(key);
    }

    public String get(String group, String key) {
        return app.get(group, key);
    }

}
